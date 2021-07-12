# adviters-virtual-wallet

# Virtual Wallet Adviters

_Ejercicio que consiste en la creación de una villetera virtual que pueda manejar en principio 2 tipos de monedas: Dólar (USD) y Bitcoin (BTC), poder hacer operaciones de acumulación y extracción de montos y poder consultar las cotizaciones del BTC con los datos consumidos de una API: https://cex.io/api/last_price/BTC/USD_

## Comenzando *IMPORTANTE* 🚀

_Se tomó como moneda principal para hacer el ejercicio el Dólar (USD), ya que para consultar la cotización del Bitcoin (USD), se expresa en base a ésta primera. Por eso la cotización del Dólar (USD) siempre será 1 ya que sino se tendría que expresarse en otro tipo de moneda y no está especificado._

_Se cargan en la base de datos automáticamente los dos tipos de monedas a utilizar en la clase principal de la aplicación._

_Al iniciar la aplicación se ejecuta el llamado a la API de cotización cada 10 segundos, y se van persistiendo los datos en la entidad de Quotation._

## Diagrama de clases  

![alt text](https://github.com/marianoponte/adviters-virtual-wallet/blob/main/diagrama_solucion.png)

### Pre-requisitos 📋

Tecnologías utilizadas para correr la aplicación:

Java 11

Gradle 7.1.1

Docker 20.10.7

## Ejecutando las pruebas ⚙️

Una vez descargado el código, ejecutar el siguiente comando para crear el archivo JAR de la aplicación:
```
gradle build
```

Despúes con el siguiente comando se levantará la aplicación y la base de datos postgres especificada en el archivo docker-compose.yml.
```
docker-compose up --build
```


Las pruebas se pueden realizar con Postman.

### 1) Para crear la billetera virtual:

Método: POST

URL: http://localhost:8090/wallet

Header: Content-Type: application/json

Request: - 

### 2) Para consultar los CurrencyWallets asociados a la billetera virtual creada. Se generan automáticamente al crear la billetera, es 1 por moneda en la aplicación y sirven para ver los saldos:

Método: GET

URL: http://localhost:8090/wallet/{id}/balances

### 3) Para crear transacciones de acumulo o de extracción (Se debe tener ya creada una billetera y especificar el CurrencyWallet asociado a ésta).

Los tipos de transacción son: ACU (Acumulo) y EXT (Extracción)

El tipo de moneda a utilizar será en base al CurencyWallet especificado en el request, ya que cada uno tiene asociado un único tipo de moneda.

Método: POST

URL: http://localhost:8090/transaction

Header: Content-Type: application/json

Request (ejemplo): {
    "idCurrencyWallet": 10,
    "transactionType": "EXT",
    "amount": 222
}

### 4) Consulta de todas las cotizaciones de Bitcoin (BTC)

Método: GET

URL: http://localhost:8090/quotations

### 5) Consulta de cotización de Bitcoin (BTC) en una fecha y hora determinada. (Si no se tiene registro de esa fecha y hora se trae el mas reciente)

Formato de fecha: yyyy-MM-ddThh:MM:ss

Método: GET

URL: http://localhost:8090/quotations?date={yyyy-MM-ddThh:MM:ss} Ejemplo: http://localhost:8090/quotations?date=2021-07-12T15:49:29


### 6) Consulta de la cotización de Bitcoin (BTC) más alta en un período determinado:

Formato de fecha: yyyy-MM-ddThh:MM:ss

Método: GET

URL: http://localhost:8090/quotations/max?from={yyyy-MM-ddThh:MM:ss}&to={yyyy-MM-ddThh:MM:ss} Ejemplo: http://localhost:8090/quotations/max?from=2021-07-12T15:49:38&to=2021-07-12T15:49:58


