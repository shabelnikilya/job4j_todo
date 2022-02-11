CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    first_name TEXT,
    second_name TEXT,
    email TEXT,
    password TEXT,
    registration TIMESTAMP
);
CREATE TABLE IF NOT EXISTS items(
	id SERIAL PRIMARY KEY,
	description TEXT,
	created TIMESTAMP,
	done BOOLEAN,
	users_id INT NOT NULL REFERENCES users(id)
);
