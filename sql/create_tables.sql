CREATE
DATABASE crudhibernate;

\c crudhibernate

CREATE TABLE IF NOT EXISTS labels
(
    id   SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS posts
(
    id      SERIAL NOT NULL,
    name    VARCHAR(50) NOT NULL,
    content VARCHAR(250),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS writers
(
    id   SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS labels_posts
(
    label_id INT NOT NULL,
    post_id  INT NOT NULL,
    FOREIGN KEY (label_id) REFERENCES labels (id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (label_id) REFERENCES labels (id) ON UPDATE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS posts_writers
(
    post_id   INT NOT NULL,
    writer_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (writer_id) REFERENCES writers (id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON UPDATE CASCADE,
    FOREIGN KEY (writer_id) REFERENCES writers (id) ON UPDATE CASCADE
);


