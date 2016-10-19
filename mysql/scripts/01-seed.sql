USE amt;

INSERT INTO users(username, password, email, first_name, last_name)
VALUES('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin@localhost', 'Admin', 'McAdminton');

INSERT INTO pokemons(name, type)
VALUES('Pikachu', 'Electric'), ('Charmander', 'Fire'), ('Squirtle', 'Water'), ('Venusaur', 'Plant');