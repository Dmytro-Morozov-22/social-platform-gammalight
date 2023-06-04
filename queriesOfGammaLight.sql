drop database if exists gammalight;
create database gammalight char set utf8;
use gammalight;


CREATE TABLE users (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, 
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);


CREATE TABLE posts (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    post_text VARCHAR(500) NOT NULL,
    publication_date TIMESTAMP DEFAULT NOW(),
    user_id BIGINT NOT NULL
);
 ALTER TABLE posts ADD FOREIGN KEY (user_id) REFERENCES users(id);


CREATE TABLE sponsored_post (
     id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
     sponsor_id BIGINT NOT NULL,
     posts_id BIGINT UNIQUE NOT NULL
);
ALTER TABLE sponsored_post ADD FOREIGN KEY (posts_id) REFERENCES posts(id) ON DELETE CASCADE;


CREATE TABLE like_post (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);
ALTER TABLE like_post ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE like_post ADD FOREIGN KEY (post_id) REFERENCES posts(id);


CREATE TABLE pictures (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    picture_link VARCHAR(255) NOT NULL,
	post_id BIGINT NOT NULL
    );
ALTER TABLE pictures ADD FOREIGN KEY (post_id) REFERENCES posts(id);


CREATE TABLE comments (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(1000) NOT NULL,
    created TIMESTAMP DEFAULT NOW(),
	user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL
    );
ALTER TABLE comments ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE comments ADD FOREIGN KEY (post_id) REFERENCES posts(id);


CREATE TABLE like_comment (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    comment_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);
ALTER TABLE like_comment ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE like_comment ADD FOREIGN KEY (comment_id) REFERENCES comments(id);


CREATE TABLE avatar (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    avatar_link VARCHAR(255) NOT NULL,
    active TINYINT DEFAULT 0,
    created TIMESTAMP DEFAULT NOW(),
	user_id BIGINT NOT NULL
    );
ALTER TABLE avatar ADD FOREIGN KEY (user_id) REFERENCES users(id);


CREATE TABLE like_avatar (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    avatar_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);
ALTER TABLE like_avatar ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE like_avatar ADD FOREIGN KEY (avatar_id) REFERENCES avatar(id);


CREATE TABLE notifications (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    followed_user_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);
ALTER TABLE notifications ADD FOREIGN KEY (user_id) REFERENCES users(id);


CREATE TABLE followers (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	followed_user_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL
);
ALTER TABLE followers ADD FOREIGN KEY (user_id) REFERENCES users(id);


CREATE TABLE friends (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	friend_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL
);
ALTER TABLE friends ADD FOREIGN KEY (user_id) REFERENCES users(id);


-- DQL COMMANDS:
-- SELECT * FROM like_avatar;
-- SELECT * FROM like_comment;
-- SELECT * FROM like_post;
-- SELECT count(id) FROM like_avatar; 
-- DELETE FROM like_avatar WHERE id = 27;
-- SELECT * FROM users u LEFT JOIN avatar a ON a.user_id = u.id;
-- SELECT * FROM users u INNER JOIN posts p ON p.user_id = u.id;
-- SELECT * FROM users u  INNER JOIN posts p ON p.user_id = u.id;
-- SELECT * FROM posts p INNER JOIN pictures pic ON pic.post_id = p.id;
-- SELECT * FROM posts p RIGHT JOIN pictures pic ON pic.post_id = p.id;
-- SELECT * FROM posts p INNER JOIN comments com ON com.post_id = p.id;
-- SELECT * FROM users u INNER JOIN comments com ON com.user_id = u.id;
-- SELECT sp.posts_id FROM sponsored_post sp INNER JOIN posts p ON sp.posts_id = p.id WHERE p.user_id = 2;
-- SELECT * FROM users  JOIN avatar ON avatar.user_id = users.id;
-- SELECT * FROM posts WHERE id IN (SELECT sp.posts_id FROM sponsored_post sp INNER JOIN posts p ON sp.posts_id = p.id WHERE p.user_id = 2);
 SELECT * FROM users;
-- SELECT * FROM posts;
-- SELECT * FROM sponsored_post;
-- SELECT * FROM pictures;
-- SELECT * FROM posts WHERE id = 7;
-- SELECT * FROM posts;
-- SELECT * FROM comments;
-- SELECT * FROM followers;
-- SELECT * FROM notifications;
-- SELECT * FROM like_comment;
-- SELECT * FROM like_post;
-- SELECT * FROM like_avatar;
-- SELECT count(post_id) FROM like_post WHERE post_id = 40;
-- SELECT * FROM avatar;
-- DROP TABLE gammalight.posts;
-- DELETE FROM posts WHERE id > 0; 
-- SELECT * FROM Employee;
-- SELECT * FROM Employee e INNER JOIN Employee m ON  m.managerId = e.id WHERE e.salary > m.salary;
