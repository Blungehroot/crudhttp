CREATE DATABASE IF NOT EXISTS crud_http;

CREATE TABLE IF NOT EXISTS users
(
    id int(11) NOT NULL auto_increment,
    name varchar(250)  NOT NULL,
    PRIMARY KEY  (id)
);

CREATE TABLE IF NOT EXISTS media
(
    id int(11) NOT NULL auto_increment,
    filename varchar(250)  NOT NULL,
    filelink varchar(1500)  NOT NULL,
    PRIMARY KEY  (id)
);

CREATE TABLE IF NOT EXISTS events
(
    id int(11) NOT NULL auto_increment,
    eventname varchar(250)  NOT NULL,
    media_id int(11),
    user_id int(11),
    PRIMARY KEY  (id),
    FOREIGN KEY (media_id) REFERENCES media (id) ON DELETE CASCADE,
    FOREIGN KEY (media_id) REFERENCES media (id) ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE
);

/*CREATE TABLE IF NOT EXISTS user_events
(
    user_id INT NOT NULL,
    event_id  INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS event_medias
(
    event_id   INT NOT NULL,
    media_id INT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    FOREIGN KEY (media_id) REFERENCES media (id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON UPDATE CASCADE,
    FOREIGN KEY (media_id) REFERENCES media (id) ON UPDATE CASCADE
);*/