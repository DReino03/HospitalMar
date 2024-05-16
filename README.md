# APP Hospital del Mar
Realitzat per l'equip de DAM del Institut Tecnològic de Barcelona (ITB): 
- [Alejandro Torres](https://github.com/alextorrees)
- [Gerard Sanchez](https://github.com/Gesa09)
- [Marçal Herraiz](https://github.com/MarcalHerraiz)
- [Daniel Reinosa](https://github.com/DReino03)

# APP
**APP Hospital del Mar** consisteix en desenvolupar una aplicació mòbil per a l'avaluació de les diferents habilitats dels alumnes en l'àrea de salut, amb la finalitat d'integrar totes les puntuacions en un sistema centralitzat. Tenim l'objectiu de crear una aplicació intuïtiva per a l'avaluació de competències transversals mitjançant rúbriques validades, permetent així el seguiment del progrés de l'alumne en funció del seu nivell objectiu.

La nostra proposta inclou la generació d'un informe final sobre l'estat de l'alumne, amb un enfocament especial en la protecció de les dades personals. L'aplicació estarà disponible en tauletes i proporcionarà funcionalitats pràctiques com ara el registre d'alumnes, l'assignació de rúbriques i la generació d'informes de manera senzilla i eficient.

Aquesta aplicació està específicament dissenyada per a la seva implementació en l'entorn de l'Hospital del Mar, assegurant la seva adaptabilitat i utilitat en aquest context específic.

# Funcionalitats Professor
## Avaluació mitjançant rúbriques 
Proporcionarà una interfície clara i intuïtiva per a l'avaluació de les habilitats dels alumnes basada en les rúbriques assignades. El professor podrà registrar les puntuacions i les observacions pertinents per a cada criteri d'avaluació.

## Seguiment del progrés:
Permetrà al professor visualitzar i fer seguiment del progrés individual de cada alumne al llarg del temps, identificant àrees de fortalesa i àrees per millorar.

## Generació d'informes: 
Facilitarà la generació d'informes detallats sobre el rendiment i l'evolució de l'alumne, incloent-hi les puntuacions obtingudes en cada rúbrica, els comentaris del professor i altres indicadors rellevants.

# Funcionalitats Alumne
## Accés als resultats:
Permetria als alumnes visualitzar les seves puntuacions i els comentaris associats a les avaluacions realitzades pel professor.

## Seguiment del progrés personal
Proporcionaria una visió del progrés personal de l'alumne en diferents àrees d'habilitats al llarg del temps, destacant els punts forts i les àrees de millora.

# Tecnologies utilitzades més rellevants
- Llenguatge de programació: [Kotlin](https://kotlinlang.org/)
- Desenvolupament de interfícies:[Jetpack Compose](https://developer.android.com/develop/ui/compose/documentation?hl=es-419)
- Framework: [Android Studio](https://developer.android.com/studio)
- Base de Dades: [PostgreSQL](https://www.postgresql.org/)

# Estructura utilizada
El patró d'arquitectura Model-Vista-ViewModel (MVVM) és l'enfocament utilitzat per a dissenyar aquesta aplicacion en Android Studio. En aquest patró, el model representa les dades i la lògica de negoci de l'aplicació, la vista representa la interfície d'usuari (UI) i el ViewModel actua com un intermediari entre la vista i el model.

# Algunes Llibreries externes
- [Compose](https://developer.android.com/develop/ui/compose?hl=es-419)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html).
- [Retrofit](https://square.github.io/retrofit/).
- [Navigation Compose](https://developer.android.com/develop/ui/compose/navigation?hl=es-419).

## Licencia
[MIT License](https://github.com/DReino03/HospitalMar/blob/master/MIT%20License)

# BackEnd
[Accés al BackEnd](https://github.com/alextorrees/API_HospitalMar)


