## REST
* Falende authenticatie geeft een 500. Dat impliceert een server fout. Maar het is de gebruiker die verkeerde input geeft. Moet het dan niet in de 400 range zitten?
* DTO's moeten echt geen entiteiten bevatten! DTO's bevatten alleen DTO's of id's van entiteiten!

## Security
* Als ik de security config goed begrijp (en de urls aanpas naar bestaande) kan ik alle gebruikersinformatie ophalen zonder ingelogd te zijn. Inclusief (encrypted) wachtwoorden.

## Tests
* Ik zie voornamelijk happy flows.
* De database wordt niet echt getest. Je moet wel testen of dingen echt gepersist worden! Dat is het punt van de repository dus dat moet je testen!
* Jullie hebben geen unit tests, alleen integratie tests. Integratietesten doe je voor de hele applicatie, unit testen voor klassen.
