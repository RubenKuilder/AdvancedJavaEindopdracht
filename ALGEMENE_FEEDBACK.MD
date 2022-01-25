## REST
* Falende authenticatie geeft een 500. Dat impliceert een server fout. Maar het is de gebruiker die verkeerde input geeft. Moet het dan niet in de 400 range zitten?
* DTO's moeten echt geen entiteiten bevatten! DTO's bevatten alleen DTO's of id's van entiteiten!

## Security
* Als ik de security config goed begrijp (en de urls aanpas naar bestaande) kan ik alle gebruikersinformatie ophalen zonder ingelogd te zijn. Inclusief (encrypted) wachtwoorden.

## Tests
* Ik zie voornamelijk happy flows.
* De database wordt niet echt getest. Je moet wel testen of dingen echt gepersist worden! Dat is het punt van de repository dus dat moet je testen!
* Jullie hebben geen unit tests, alleen integratie tests. Integratietesten doe je voor de hele applicatie, unit testen voor klassen.


De indruk die ik krijg van jullie project is dat jullie heel veel geleerd hebben en de verschillende tools in de basis begrijpen. En dat is een ontzettend goed begin!

Maar jullie moeten wel wat scherper zijn op het gebruik van de verschillende tools. Het meest schrijnende voorbeeld is de beveiliging.Het werkt in de basis, maar het wordt niet goed gebruikt waardoor eigenlijk alles open staat. Nu is dit geen minor met een hele grote focus op security, maar als ik dit bij een code review tegen zou komen in een organisatie dan komt dit er natuurlijk niet doorheen.

Ook bij het testen is het denk ik verstandig om wat dieper na te gaan denken over wat je wilt gaan testen. Ten eerste, jullie hebben voornamelijk getest op de happy flow. Dit is zeker belangrijk, maar het is zeker net zo belangrijk (en misschien nog wel belangrijker) om te testen wat er gebeurt als het fout gaat. Wat als de gebruiker verkeerde input geeft? Of niet de juiste rol heeft voor dat endpoint?

Dan nog het feit dat jullie alleen integratietesten doen. Integratietesten zijn heel erg goed, om te zien of alles goed met elkaar integreert (vandaar de naam). Maar als je de service laag test, hoef ik de rest niet op te tuigen. Het is dan veel makkelijker om de repository laag te mocken.

Al met al mogen jullie er nog steeds trots op zijn. Jullie hebben duidelijk veel geleerd en beheersen nu de basis van verschillende tools en concepten die in het werkveld veel worden gebruikt. Maar blijf scherp. Blijf jezelf en elkaar (kritische) vragen stellen:

- Waarom doe ik/je dit zo?

- Wat als het fout gaat?

- Wat wil ik/je naar mijn gebruiker communiceren als het fout gaat?

- Wat heb ik/je nodig om de werking van deze klasse goed te testen?

- Hoe zorg ik/je ervoor dat zo min mogelijk mensen bij deze data kunnen?

- Kan dit op een makkelijkere manier?

Blijf hier aan werken en dan komen jullie heel ver! Succes en bedankt voor jullie enthousiasme!
