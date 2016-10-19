# Rest Api User Manual:

## User Rest Api:

The User Rest Api is used to administrate users. By using it you can do the following action on the corresponding site:

1. Add user
2. Update password of a particular user
3. Update informations of a particular user
4. Show informations of all users
5. Show informations of a particular user

### Add user

It allow you to add a user. Therefor you have tu use http's *post* method and send to the following address a *json* object of this type: 

**Address:**

`POST /app/api/users`

**Json content type:**
```
{
    "username": "John10",
    "firstname": "John",
    "lastname": "Doe",
    "password": "1234",
    "email": "john.doe@heig-vd.ch"
}
```

### Update password of a particular user

It allow you to update the password of a user. Therefor you have to use http's *put* method and send to the following address a *json* object of this type:

**Address:**

`PUT /app/api/users/update/password/:username`

where *:username* correspond to the username of the targeted user. 

**Json content type:**
```
{
    "password": "NewPassword"
}
```

### Update informations of a particular user.

It allow you to update the personal information of a targeted user. Therefor you have to use http's *put* method and send to the following address a *json* object of this type:

**Address:**

`PUT /app/api/users/update/user/:username`

where *:username* correspond to the username of the targeted user. 

**Json content type:**
```
{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@heig-vd.ch"
}
```

### Show informations of all users.

It allow you to show the personal information of all users. Therefor you have to use http's *get* method. Notice that you have to add *Content-Type: application/json* in your header to informe the server that you accept json response.

**Address:**

`GET /app/api/users`

You will then receive that kind of response:

**Json content type:**
```
[
  {
    "username": "john10",
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@heig-vd.ch"
  },
  {
    "username": "luc",
    "firstname": "Luc",
    "lastname": "Skywalker",
    "email": "luc.skywalker@heig-vd.ch"
  }
]
```

### Show informations of a particular user.

It allow you to show the personal informations of a targeted user. Therefor you have to use http's *get* method. Notice that you have to add *Content-Type: application/json* in your header to informe the server that you accept *json* response.

**Address:**

`GET /app/api/:username`

Where *:username* correspond to the username of the targeted user. 
You will then receive that kind of response:

**Json content type:**
```
{
	"username": "john10",
	"firstname": "John",
	"lastname": "Doe",
	"email": "john.doe@heig-vd.ch"
}
```

##2. Pokemon Rest Api:

The Pokemon Rest Api allow the customer to make CRUD operations on pokemons stored in the database.

1. Create a pokemon
2. Show informations of all pokemons
3. Show information of a particular pokemon
4. Update a particular pokemon
5. Delete a particular pokemon

### Create a pokemon

It allow you to register a pokemon. Therefore, you have to use http's *post* method and send to the following address a *json* object of this type: 

**Address:**

`POST /app/api/pokemons`

**Json content type:**
```
  {
    "name": "Squirtle",
    "type": "Water"
  }
```

### Show informations of all pokemons

It allow you to show all registered pokemons. Therefor you have to use http's *get* method. Notice that you have to add *Content-Type: application/json* in your header to informe the server that you accept *json* response.

**Address:**

`GET /app/api/pokemons`

You will then receive that kind of response:

**Json content type:**
```
[
  {
    "name": "Pikachu",
    "type": "Electric",
    "id": 1
  },
  {
    "name": "Charmander",
    "type": "Fire",
    "id": 2
  }
]
```
### Show information of a particular pokemon

It allow you to show informations of a registered pokemon. Therefor you have to use http's *get* method. Notice that you have to add *Content-Type: application/json* in your header to informe the server that you accept *json* response.

**Address:**

`GET /app/api/pokemons/:id`

Where *:id* is a number respectively the id of the targeted pokemon.
You will then receive that kind of response:

**Json content type:**
```
{
	"name": "Pikachu",
	"type": "Electric",
	"id": 1
}
```

### Update a particular pokemon

It allow you to update informations of a targeted pokemon. Therefor you have to use http's *put* method and send to the following address a *json* object of this type:

**Address:**

`PUT /app/api/pokemons/:id`

Where *:id* is a number respectively the id of the targeted pokemon.

**Json content type:**
```
  {
    "name": "Carapace",
    "type": "Eau"
  }
```

### Delete a particular pokemon

It allow you to delete a registered pokemon. Therefor you have to use http's *del* method and send the request to the following address:

**Address:**

`DEL /app/api/pokemons/:id`

Where *:id* is a number respectively the id of the targeted pokemon.

