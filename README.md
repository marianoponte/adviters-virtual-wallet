# adviters-virtual-wallet

# Virtual Wallet Adviters

_Ejercicio que consiste en la creaci贸n de una villetera virtual que pueda manejar en principio 2 tipos de monedas: D贸lar (USD) y Bitcoin (BTC), poder hacer operaciones de acumulaci贸n y extracci贸n de montos y poder consultar las cotizaciones del BTC con los datos consumidos de una API: https://cex.io/api/last_price/BTC/USD_

## Comenzando *IMPORTANTE* 馃殌

_Se tom贸 como moneda principal para hacer el ejercicio el D贸lar (USD), ya que para consultar la cotizaci贸n del Bitcoin (USD), se expresa en base a 茅sta primera. Por eso la cotizaci贸n del D贸lar (USD) siempre ser谩 1 ya que sino se tendr铆a que expresarse en otro tipo de moneda y no est谩 especificado._

_Se cargan en la base de datos autom谩ticamente los dos tipos de monedas a utilizar en la clase principal de la aplicaci贸n._

_Al iniciar la aplicaci贸n se ejecuta el llamado a la API de cotizaci贸n cada 10 segundos, y se van persistiendo los datos en la entidad de Quotation._

## Diagrama de clases  

![alt text](https://github.com/marianoponte/adviters-virtual-wallet/blob/main/diagrama_solucion.png)

### Pre-requisitos 馃搵

Tecnolog铆as utilizadas para correr la aplicaci贸n:

Java 11

Gradle 7.1.1

Docker 20.10.7

## Ejecutando las pruebas 鈿欙笍

Una vez descargado el c贸digo, ejecutar el siguiente comando para crear el archivo JAR de la aplicaci贸n:
```
gradle build
```

Desp煤es con el siguiente comando se levantar谩 la aplicaci贸n y la base de datos postgres especificada en el archivo docker-compose.yml.
```
docker-compose up --build
```


Las pruebas se pueden realizar con Postman.

### 1) Para crear la billetera virtual:

M茅todo: POST

URL: http://localhost:8090/wallet

Header: Content-Type: application/json

Request: - 

### 2) Para consultar los CurrencyWallets asociados a la billetera virtual creada. Se generan autom谩ticamente al crear la billetera, es 1 por moneda en la aplicaci贸n y sirven para ver los saldos:

M茅todo: GET

URL: http://localhost:8090/wallet/{id}/balances

### 3) Para crear transacciones de acumulo o de extracci贸n (Se debe tener ya creada una billetera y especificar el CurrencyWallet asociado a 茅sta).

Los tipos de transacci贸n son: ACU (Acumulo) y EXT (Extracci贸n)

El tipo de moneda a utilizar ser谩 en base al CurencyWallet especificado en el request, ya que cada uno tiene asociado un 煤nico tipo de moneda.

M茅todo: POST

URL: http://localhost:8090/transaction

Header: Content-Type: application/json

Request (ejemplo): {
    "idCurrencyWallet": 10,
    "transactionType": "EXT",
    "amount": 222
}

### 4) Consulta de todas las cotizaciones de Bitcoin (BTC)

M茅todo: GET

URL: http://localhost:8090/quotations

### 5) Consulta de cotizaci贸n de Bitcoin (BTC) en una fecha y hora determinada. (Si no se tiene registro de esa fecha y hora se trae el mas reciente)

Formato de fecha: yyyy-MM-ddThh:MM:ss

M茅todo: GET

URL: http://localhost:8090/quotations?date={yyyy-MM-ddThh:MM:ss} Ejemplo: http://localhost:8090/quotations?date=2021-07-12T15:49:29


### 6) Consulta de la cotizaci贸n de Bitcoin (BTC) m谩s alta en un per铆odo determinado:

Formato de fecha: yyyy-MM-ddThh:MM:ss

M茅todo: GET

URL: http://localhost:8090/quotations/max?from={yyyy-MM-ddThh:MM:ss}&to={yyyy-MM-ddThh:MM:ss} Ejemplo: http://localhost:8090/quotations/max?from=2021-07-12T15:49:38&to=2021-07-12T15:49:58


