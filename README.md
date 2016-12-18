# Oispakaljaa

### Linkki sovellukseen ja koodiin
https://github.com/OispaKaljaa/oispakaljaa

http://oispaolutta.herokuapp.com

### Rest API
http://oispaolutta.herokuapp.com/api/

http://oispaolutta.herokuapp.com/api/bars

<b>Key:</b><br>
name<br>
longitude<br>
latitude<br>
Address

http://oispaolutta.herokuapp.com/api/bars/{barId}/drinks

<b>Key:</b><br>
name<br>
drinkType<br>
price<br>
volume<br>
alcoholPercentage

### Sovelluksen kuvaus

- Käyttäjät voivat lisätä järjestelmään baareja. He ilmoittavat baarin nimen ja sijainnin, jolloin lisäys menee ylläpidon hyväksyttäväksi.
- Käyttäjät voivat lisätä järjestelmään tiedon baarin edullisimmasta oluesta sekä suosituksia.
- Kuka tahansa voi hakea edullisinta järjestelmään syötettyä olutta ja nähdä täten, mikä baari tarjoaa edullisimman oluen. Hakua voi rajata tietylle alueelle sijainnin mukaan. Hyödynnämme tässä Google Mapsin API:a.
- Kuka tahansa voi hakea parasta ravintolaa myös suositusten perusteella.

### Jatkokehitysideoita

- Käyttäjä voi valita juoman tyypin (esim. olut, lonkero).

