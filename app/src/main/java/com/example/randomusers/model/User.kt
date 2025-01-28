package com.example.randomusers.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val gender: String? = null,
    val name: Name? = null,
    val email: String? = null,
    val phone: String? = null,
    val picture: Picture? = null,
    val location: Location? = null,
//    val street: Street? = null,
)

@Serializable
data class Name(
    val title: String,
    val first: String,
    val last: String,
)

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String,
)

@Serializable
data class Location(
    val city: String,
    val state: String,
    val country: String,
    val street: Street? = null,
)

@Serializable
data class Street(
    val number: Int ? = 0,
    val name: String ? = "",
)

/*{
  "results": [
    {
      "gender": "female",
      "name": {
        "title": "Miss",
        "first": "Lucía",
        "last": "Véliz"
      },
      "location": {
        "street": {
          "number": 2312,
          "name": "Circunvalación Turquía"
        },
        "city": "San Vicente",
        "state": "Tlaxcala",
        "country": "Mexico",
        "postcode": 53510,
        "coordinates": {
          "latitude": "21.9638",
          "longitude": "85.5471"
        },
        "timezone": {
          "offset": "+4:30",
          "description": "Kabul"
        }
      },
      "email": "lucia.veliz@example.com",
      "login": {
        "uuid": "9f4a36ce-7980-4c82-9517-29082df30f95",
        "username": "heavycat631",
        "password": "baritone",
        "salt": "LHaXHhuv",
        "md5": "9c9030b7fdb63678aea27f616a665d4b",
        "sha1": "603a044f61cd6a2b61c3c533d6dc78d8cec3ade6",
        "sha256": "b90efb44e9735b7b038cfcca782838ac724e8b78f7c1a1efb916ef470681937e"
      },
      "dob": {
        "date": "1948-01-28T23:45:28.767Z",
        "age": 76
      },
      "registered": {
        "date": "2013-03-12T05:32:09.545Z",
        "age": 11
      },
      "phone": "(689) 209 5319",
      "cell": "(611) 388 3955",
      "id": {
        "name": "NSS",
        "value": "27 32 72 3505 3"
      },
      "picture": {
        "large": "https://randomuser.me/api/portraits/women/14.jpg",
        "medium": "https://randomuser.me/api/portraits/med/women/14.jpg",
        "thumbnail": "https://randomuser.me/api/portraits/thumb/women/14.jpg"
      },
      "nat": "MX"
    }
  ],
  "info": {
    "seed": "4569885d812749c6",
    "results": 1,
    "page": 1,
    "version": "1.4"
  }
}*/