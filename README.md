# oispakaljaa
![build status](https://api.travis-ci.org/OispaKaljaa/oispakaljaa.svg?branch=master)
In progress aggregator for finding cheapest beers and bars by location. 

## API 
### Entrypoint
 `http://oispaolutta.herokuapp.com/api`
 
### Structure
```
{
  "status": "OK",
  "message": "",
  "data": { ... }
}
```
### Response models

#### Bar
The `Bar` object contains the following properties:

Key | Description
---- | -----------
`id` | **Number**. The unique ID of the bar.
`name` | Name of the bar.
`address` | Address of the bar.

##### Example
```
{
  "id": "1",
  "name": "klusteri",
  "address": "leppäsuonkatu+11+helsinki"
}
```

#### Drink
The `Drink` object contains the following properties:

Key | Description
----|------------
`id` | **Number**. The unique ID of the drink.
`name` | Name of the drink.
`drinkType` | Type of the drink.
`price` | **Number**. Price of the drink.
`volume` | **Number**. Volume of the drink.
`alcoholPercentage` | **Number**. Alcohol Percentage of the drink.

##### Example
```
 {
"id": 1,
"name": "karjala",
"drinkType": "beer",
"price": 550,
"volume": 5,
"alcoholPercentage": 456
 }
```

# Endpoints

Path | Verb | Description
-----|------|------------
`/bars/` | GET | Returns an array of all bars.
`/bars/` | POST | Creates a new bar.
`/bars/:barId` | GET | Gets a specific bar.
`/bars/:barId/drinks/` | GET | Gets drinks of a bar.
`/bars/:barId/drinks/` | POST | Creates a new drink on the specified bar.
