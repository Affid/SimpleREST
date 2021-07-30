
# SimpleREST
- This is example of pretty simple REST Api written in pure Java with using of HttpServer class fromcom.sun.
- It stores different beer objects inside Postgresql database and responds with JSON
- Currently it supports only GET & POST requests which are pretty limited
### What could you do with it?
Well it uses PostgreSQL as data storage and expects what table are already created with this example.

    create table beer ( internal_id serial primary key, label varchar(50) unique not null,type varchar(10) not null, price integer not null, flavor varchar(80) not null);

There is only 5(!) possible requests for this API, so here is the example:

- `GET /api/ping` - Gives you pong
 
- `GET /api/db` - Tests your postgresql connection
 
- `GET /api/db/beer` - Gives you JSON with whole beer in the table
 
- `GET /api/db/beer/ale` - Similar to /beer, yeah I need to refactor this

- `GET /api/db/beer/lager` - Similar to /beer, yeah I need to refactor this
 
	 Example:

	    [
	       {
	          "flavor":"Classic English ale with smouth fruit flavor",
	          "price":150,
	          "label":"The Brown Note",
	          "type":"Ale"
	       },
	       {
	          "flavor":"Some kind of JavaScript flavor text",
	          "price":201,
	          "label":"JsonifyAle",
	          "type":"Ale"
	       },
	       {
	          "flavor":"Some kind of JavaScript flavor text",
	          "price":201,
	          "label":"JsonifyLager",
	          "type":"Lager"
	       }
	    ]

- `POST /api/db/beer` - Load JSON to database

	Example with curl

	    curl -X POST \
	    -H 'Content-Type: application/json' \
	    -d '{"type":"Lager",\
		    "label":"JsonifyLager", \ 
		    "price":201, \
		    "flavor":"Some kind of JavaScript flavor text" \
		    }' \
	    localhost:5000/api/db/beer


