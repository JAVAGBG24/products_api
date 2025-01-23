# Products API

# Innan du gör nåt annat se till att:
1. Du har en **application.yaml** fil i din **resources** folder och att du har DELETE application.properties filen, du kan inte ha båda!
2. Se också till du har Dockerfile och docker-compose.yaml i root foldern

# Fixa connection till mongoDB compass
1. I compass välj "New Connection"
2. Lägg den här urlen som connection string:
```mongodb://myuser:mypassword@localhost:27017/demodb?authSource=admin```
3. Editera connection välj **Advanced Connection Options** och väl **Authentication** ta bort user name och password så input är tomt
4. Klicka på save & connect, tips döp om din connection innan.
5. Öppna mongosh i din connection och kör:
```
db.getSiblingDB('admin').createUser(
{
user:"myuser",
pwd:"mypassword",
roles: ["root"]
})
```
Du ska få svar typ nåt sånt här ok 1.

6. stäng ner Compass
7. starta Docker, till att du är i din projekt folder och kör **docker-compose up -d**
8. starta **Compass** igen och gå in i **Advanced Connection Options** på din Docker connection.
9. ändra tillbaka **Authentication** fyll i user och password:
```
user: myuser
password: mypassword
```

## Query methods
Query methods är en riktigt bra del av Spring Data MongoDB som bygger på 
något som kallas för "method name parsing".

När du skapar ett interface som ärver från MongoRepository, använder Spring något 
som kallas för **"method name parsing"**. Spring analyserar metodnamnen i ditt interface 
och översätter dem automatiskt till MongoDB-queries.

Ta till exempel metoden **findByName(String name)**. När Spring ser denna metod, 
bryter den ner namnet i olika delar:

1. "find" - indikerar att det är en sökoperation
2. "By" - markerar att det som följer är villkoret
3. "Name" - specificerar vilket fält i din Product model som ska användas

**Spring matchar "Name" mot ett fält som heter "name" i vår Product-klass**

När du anropar findByName("telefon"), översätter Spring detta automatiskt till en 
MongoDB-query som motsvarar: { "name": "telefon" }.

**Spring Data MongoDB stödjer många sådana nyckelord som du kan använda i metodnamnen:**
1. And, Or för att kombinera villkor
2. GreaterThan, LessThan för jämförelser
3. Contains för partiella matchningar
4. OrderBy för sorteringgit add .

Det fina med detta system är att du inte behöver skriva någon implementering själv 
- Spring skapar alla queries åt dig baserat på metodnamnen. 
- Detta minskar risken för fel och gör koden mer underhållbar.

## @ControllerAdvice
I en typisk Spring Boot-applikation har du många controllers som hanterar olika 
HTTP-requests. Varje controller kan stöta på olika typer av fel - kanske hittas inte en 
resurs, kanske skickar användaren ogiltig data, eller kanske kraschar något internt.

Utan @ControllerAdvice skulle du behöva hantera dessa fel i varje enskild controller, 
vilket skulle leda till mycket duplicerad kod. 
Det här problemet löser @ControllerAdvice. Det låter dig definiera en central plats 
för all felhantering.

## @PathVariable och @RequestParam
Skillnaden mellan @PathVariable och @RequestParam är ungefär som olika sätt att skicka information i en URL, 
ungefär som två olika typer av adresslappar på ett paket.

@PathVariable används när informationen är en del av själva URL-strukturen, 
som en del av "vägen" till resursen. Tänk på det som gatuadressen på ditt paket. 

@RequestParam används istället för valfria parametrar som läggs till i slutet av URL:en 
efter ett frågetecken. Det är mer som extra instruktioner på paketet.

### använd @PathVariable
```
// för att identifiera en specifik resurs
@GetMapping("/users/{userId}")
@GetMapping("/users/{userId}/orders/{orderId}")

// för hierarkiska relationer
@GetMapping("/departments/{deptId}/employees/{empId}")

// för RESTful URL-struktur
@DeleteMapping("/products/{productId}")
```

### använd @RequestParam
```
// för filtrering
@GetMapping("/products")
public List<Product> getProducts(@RequestParam(required = false) String color,
                               @RequestParam(required = false) Double minPrice) {
    // returnerar produkter filtrerade efter färg och pris
}

// för sökning
@GetMapping("/search")
public List<Product> searchProducts(@RequestParam String query) {
    // söker efter produkter som matchar query
}

// för paginering
@GetMapping("/orders")
public Page<Order> getOrders(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "20") int size) {
    // returnerar en sida med orders
}
```
