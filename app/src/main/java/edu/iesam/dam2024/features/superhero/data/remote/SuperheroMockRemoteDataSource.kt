package edu.iesam.dam2024.features.superhero.data.remote

import edu.iesam.dam2024.features.superhero.domain.Appearance
import edu.iesam.dam2024.features.superhero.domain.Height
import edu.iesam.dam2024.features.superhero.domain.PowerStats
import edu.iesam.dam2024.features.superhero.domain.Superhero
import edu.iesam.dam2024.features.superhero.domain.SuperheroBiography
import edu.iesam.dam2024.features.superhero.domain.SuperheroConnection
import edu.iesam.dam2024.features.superhero.domain.SuperheroPrincipalData
import edu.iesam.dam2024.features.superhero.domain.SuperheroWork
import edu.iesam.dam2024.features.superhero.domain.Weight

class SuperheroMockRemoteDataSource {

    private val superheroes = listOf(
        Superhero(
            SuperheroPrincipalData(
                "1",
                "Superman",
                "Superman",
                "https://media.npr.org/assets/img/2011/11/30/frankface_custom-3fd12d46b7c62b91e8cd12c2f214ea92e51741a6.jpg?s=1100&c=50&f=jpeg",
                PowerStats(85, 100, 95, 100, 100, 85),
                Appearance(
                    "Male", "Kryptonian",
                    Height("191", "6'3"), Weight("107", "235.9"), "Blue", "Black"
                )
            ), SuperheroBiography(
                "Clark Kent",
                "Kal-El",
                listOf("Man of Steel", "Last Son of Krypton"),
                "Krypton",
                "Action Comics #1",
                "DC Comics",
                "good"
            ),
            SuperheroWork("Journalist", "Metropolis"),
            SuperheroConnection(
                "Justice League",
                "Jonathan Kent (adoptive father), Martha Kent (adoptive mother)"
            ),
            listOf("https://example.com/superman1.jpg", "https://example.com/superman2.jpg")
        ),
        Superhero(
            SuperheroPrincipalData(
                "2",
                "Batman",
                "batman",
                "https://static.dc.com/dc/files/default_images/Char_WhosWho_Batman_20190116_5c3fc4b40faf04.59002472.jpg",
                PowerStats(100, 40, 35, 50, 55, 100),
                Appearance(
                    "Male", "Human",
                    Height("188", "6'2"), Weight("95", "209.4"), "Blue", "Black"
                )
            ),
            SuperheroBiography(
                "Bruce Wayne",
                "N/A",
                listOf("The Dark Knight", "The Caped Crusader"),
                "Gotham City",
                "Detective Comics #27",
                "DC Comics",
                "good"
            ),
            SuperheroWork("Businessman", "Gotham City"),
            SuperheroConnection(
                "Justice League",
                "Thomas Wayne (father), Martha Wayne (mother)"
            ),
            listOf("https://example.com/batman1.jpg", "https://example.com/batman2.jpg")
        ),
        Superhero(
            SuperheroPrincipalData(
                "3",
                "Spider-Man",
                "spiderman",
                "https://i.pinimg.com/564x/67/3a/f3/673af35c7e0d091101e54202aa20a7ef.jpg",
                PowerStats(90, 55, 67, 60, 75, 85),
                Appearance(
                    "Male", "Human",
                    Height("178", "5'10"), Weight("76", "167.6"), "Hazel", "Brown"
                )
            ),
            SuperheroBiography(
                "Peter Parker",
                "N/A",
                listOf("Spidey", "Web-Slinger"),
                "New York City",
                "Amazing Fantasy #15",
                "Marvel Comics",
                "good"
            ),
            SuperheroWork("Photographer", "New York City"),
            SuperheroConnection(
                "Avengers",
                "Aunt May (aunt), Ben Parker (uncle)"
            ),
            listOf("https://example.com/spiderman1.jpg", "https://example.com/spiderman2.jpg")
        ),
        Superhero(
            SuperheroPrincipalData(
                "4",
                "Wonder Woman",
                "wonderwoman",
                "https://thanley.wordpress.com/wp-content/uploads/2015/01/ww38face3.png",
                PowerStats(85, 100, 75, 90, 100, 95),
                Appearance(
                    "Female", "Amazon",
                    Height("183", "6'0"), Weight("74", "163.1"), "Blue", "Black"
                )
            ),
            SuperheroBiography(
                "Diana Prince",
                "N/A",
                listOf("Princess Diana", "Amazon Warrior"),
                "Themyscira",
                "All Star Comics #8",
                "DC Comics",
                "good"
            ),
            SuperheroWork("Warrior", "Themyscira"),
            SuperheroConnection(
                "Justice League",
                "Hippolyta (mother)"
            ),
            listOf(
                "https://example.com/wonderwoman1.jpg",
                "https://example.com/wonderwoman2.jpg"
            )
        )
    )

    fun getSuperheroes(): List<Superhero> {
        return superheroes
    }

    fun getSuperhero(superheroId: String): Superhero? {
        return getSuperheroes().firstOrNull { superhero ->
            superhero.principalData.id == superheroId
        }
    }
}