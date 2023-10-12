# codding-challenge-deckskill-inditex
This is a small application that queries prices in a database, based on the Hexagonal architecture.

# PriceRateService

La aplicaci�n PriceRateService es una API REST para consultar precios de productos en funci�n de la fecha de aplicaci�n, el identificador de producto y el identificador de cadena.

## Uso de la Aplicaci�n

Para consultar precios de productos, realiza una solicitud HTTP GET al siguiente endpoint:

GET /api-priceRateService/find_{applicationDate}{product_id}{brand}


- `{applicationDate}`: La fecha de aplicaci�n en formato "yyyy-MM-dd HH:mm:ss".
- `{product_id}`: El identificador del producto que deseas consultar.
- `{brand}`: El identificador de la cadena a la que pertenece el producto.

Ejemplo de consulta:
GET /api-priceRateService/find_2020-06-14 10:00:00_35455_1


## Respuesta de la API

La API responder� con la informaci�n del precio correspondiente a la fecha de aplicaci�n. La respuesta incluir� los siguientes campos:

- `brandId`: El identificador de la cadena a la que pertenece el producto.
- `productId`: El identificador del producto consultado.
- `priceList`: El identificador de la tarifa de precios aplicable.
- `price`: El precio final de venta.
- `aplicationDate`: La fecha de aplicaci�n en formato "yyyy-MM-dd HH:mm:ss".

- Ejemplo de respuesta:

	```json
	{
	  "brandId": 1,
      "productId": 35455,
      "priceList": 4,
      "price": 38.95,
      "aplicationDate": "2020-06-15 17:00:00"
	} 
	```

## Errores
Si se produce un error durante la consulta, la API responder� con un c�digo de estado HTTP 500 (Internal Error).

- Ejemplo de respuesta de error (Precio no encontrado):

	```"Product price not found with data: 
	 AplicationDate : 2029-06-15 17:00:00
	 Product_id: 35455
	 Brand_id: 1"
	 ```
 
 -Ejemplo de respuesta de error (Formato de fecha incorrecto): 

	```"Incorrect date format. Should be like yyyy-MM-dd HH:mm:ss. 
	Exaple: 2029-06-15 17:00:00"
	```
	

## Base de Datos

La aplicaci�n utiliza una base de datos en memoria H2 para almacenar la informaci�n de los precios de los productos. La base de datos se inicializa con datos de ejemplo desde un archivo `import.sql`. Contiene una sola tabla llamada `PRICES`.


### Camposde la tabla:

```
BRAND_ID: identificador de la cadena.
START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
PRICE_LIST: Identificador de la tarifa de precios aplicable.
PRODUCT_ID: Identificador c�digo de producto.
PRIORITY: Desambiguador de aplicaci�n de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor num�rico).
PRICE: precio final de venta.
CURR: iso de la moneda.
```



