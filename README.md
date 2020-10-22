# Image Gallery
The app loads and caches photos.


## How to run

If you can use Docker, then just type in the project directory.

```

make run

```

### OR

- Create postgres db with name rest_gallery
- Run app
- Compile and run app

## All images as JSON

```

GET /images

```

## Page of images as JSON

```
GET /images?page=N 

```

## Full info about image as JSON

```

GET /images/${id}

```

## Search by some text (author, tags, camera)

```

GET /search/${searchTerm} - search by metadata

```
