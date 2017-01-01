## API 
### Entrypoint
 `http://oispakaljaa.herokuapp.com/api`
 
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
`nFavourites` | Number of favourites

##### Example
```
{
  "id": "1",
  "name": "klusteri",
  "address": "leppäsuonkatu+11+helsinki",
  "nFavourites": 2
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
`intoxFactor` | **Float**. Intoxication factor.

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

### Endpoints

Path | Method | Description
-----|------|------------
`/bars/` | GET | Returns an array of all bars.
`/bars/` | POST | Creates a new bar. Requires authentication (requires auth.).
`/bars/:barId` | GET | Gets a specific bar.
`/bars/:barId` | PUT | Adds a favourite (requires auth.).
`/bars/:barId/drinks/` | GET | Gets drinks of a bar.
`/bars/:barId/drinks/` | POST | Creates a new drink on the specified bar (requires auth.).
`/recommend` | GET | Gets recommendations in your area. Requires `pos` url param (`?pos=latitude,longitude`)
`/me` | GET | Gets current authenticated user (requires auth.).
