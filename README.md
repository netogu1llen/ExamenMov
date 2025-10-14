# Examen Argumentativo Práctico de Desarrollo Móvil

- Repositorio del exámen de móviles de la materia tc2007b
- Desarrollado por: Ernesto Guillén Guerrero 
- Matrícula: A01704967
- Plataforma: Android

## Descripción
Esta aplicación permite explorar información detallada sobre países:
-   Población y área territorial
-   Capital y región geográfica
-   Idiomas oficiales
-   Monedas
-   Banderas y escudos
-   Zonas horarias

La app incluye funcionalidades de búsqueda, caché local y una interfaz moderna construida con Jetpack Compose.

## Características principales

### **Funcionalidades Principales**

-   Lista de países: Visualización de todos los países
-   Detalle completo: Información exhaustiva de cada país
-   Búsqueda : Filtrado por nombre o capital en tiempo real
-   Caché local: Almacenamiento temporal para acceso offline
-   Pull-to-refresh: Actualización de datos
-   Estados de carga: Estados de carga y manejo de errores

### **Características Técnicas**

-    MVVM + Clean Architecture
-   Inyección de dependencias con Hilt
-   UI moderna con Jetpack Compose + Material 3
-   Persistencia: SharedPrefernces
### API Utilizada

**REST Countries API v2**

-   Base URL: https://restcountries.com/v2
-   Documentación: restcountries.com
-   Endpoints:
    -   `GET /AmericasCountries` - Todos los paises de America
    -   `GET /name/{name}` - Búsqueda por nombre

## Arquitectura
La aplicación sigue los principios de Clean Architecture junto con MVVM:
```
app/
├── data/                    # Capa de Datos
│   ├── local/              # Persistencia local
│   │   ├── model/          # Modelos de caché
│   │   └── preferences/    # SharedPreferences
│   ├── remote/             # Fuentes remotas
│   │   ├── api/            # API
│   │   └── dto/            # Data Transfer Objects
│   ├── repository/         # Implementación de repositorios
│   └── mapper/             # Conversión to domain│
├── domain/                  # Capa de Dominio
│   ├── model/              # Modelos de negocio
│   ├── repository/         # Contratos de repositorio
│   ├── usecase/            # Casos de uso
│   └── common/             # Utilidades comunes
│
├── presentation/            # Capa de Presentación
│   ├── screens/            # Pantallas
│   │   ├── home/           # Lista y búsqueda
│   │   └── detail/         # Detalle del país
│   ├── navigation/         # Navegación
│   ├── common/             # Componentes reutilizables
│   └── theme/              # Temas y estilos
│
└── di/                      # Dependencias
    └── AppModule.kt
```

### Relacionando etapas de desarrollo de software con metodología de administración de proyectos ágiles.
Los desarrollos de software requieren una integración continua y efectiva, también trabajar con técnicas y procesos de gestión de proyectos. Las metodologías ágiles como Scrum nos proporcionan herramientas flexibles que se adapta y asimila al ciclo de vida del desarrollo de software. 
Un ejemplo práctico puede ser en este proyecto y en la materia en general, cada sprint es un mini ciclo de vida completo, al final de cada sprint hay un incremento en la funcionalidad del producto. Se gestionan las prioridades y se busca asegrurar calidad. También la retroalimentación de un sprint pasado se incorpora al siguiente, buscando una flexibilidad y mejora continua.
