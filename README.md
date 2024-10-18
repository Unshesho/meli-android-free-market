# Free Market

Aplicación nativa de Android diseñada para facilitar la búsqueda y exploración de productos.. La aplicación consta de tres pantallas principales:
1. **Pantalla de Búsqueda**: Presenta un mensaje de bienvenida y un campo de entrada donde los usuarios pueden buscar productos.
2. **Pantalla de Listado de Productos**: Muestra los resultados de búsqueda, permitiendo al usuario ver una lista de productos relacionados. Incluye un campo de entrada adicional para que el usuario realice nuevas búsquedas rápidamente.
3. **Pantalla de Detalle de Producto**: Permite al usuario explorar más características del producto seleccionado. Incluye un campo de entrada adicional para que el usuario realice nuevas búsquedas rápidamente.

## Instalación y Ejecución
1. **Requisitos del Sistema**:
    - minSdkVersion: 24 (Android 7.0, Nougat)
    - targetSdkVersion: 34
2. **Configuración del Proyecto**:
    - La aplicación está configurada con variantes de compilación debug y release. Asegúrate de seleccionar la variante debug para todos los módulos del proyecto antes de ejecutar.
    - No se requieren dependencias adicionales fuera de las gestionadas por Gradle. Todas las librerías y configuraciones necesarias se instalarán automáticamente.
3. **Ejecución en Android Studio**:
    - Clona el repositorio del proyecto en tu máquina local.
    - Abre el proyecto en Android Studio.
    - Selecciona la variante de compilación debug para todos los módulos.
    - Conecta un dispositivo Android o utiliza el emulador de Android Studio para ejecutar la aplicación.

La arquitectura de Free Market sigue los principios de Clean Architecture, organizando el proyecto en diferentes capas para una mejor separación de responsabilidades:
1. **Capa de Data**: Gestiona la comunicación con fuentes de datos externas, como APIs o bases de datos locales, y proporciona los datos necesarios para la aplicación.
2. **Capa de Presentación**: Actúa como intermediaria entre la UI y la capa de Data. Implementa la lógica para manejar los estados de la aplicación y procesar las intenciones del usuario.
3. **Capa de UI**: Responsable de mostrar la interfaz gráfica y gestionar las interacciones del usuario.

En este proyecto, no se ha incluido una capa de dominio porque la lógica de negocio es simple y no requiere abstracciones adicionales entre la presentación y la capa de datos.

### Patrón UDF (Unidirectional Data Flow)
Para la comunicación entre la UI y la lógica de vista (ViewModel), se ha implementado un patrón similar a MVVM, conocido como UDF. Este patrón asegura que el flujo de datos sea unidireccional, facilitando la gestión de estados y la interacción del usuario. El proceso funciona de la siguiente manera:
1. **Intenciones de Usuario**: El usuario interactúa con la UI, generando una intención (por ejemplo, iniciar una búsqueda o seleccionar un producto).
2. **ViewModel**: La intención se envía al ViewModel, que la interpreta y realiza una llamada a la capa de Data para obtener la información requerida.
3. **Gestión de Estados**:
    - Mientras se realiza la solicitud, el ViewModel emite un estado de carga que la UI muestra al usuario.
    - Una vez que los datos están disponibles, el ViewModel envía un estado de éxito o estado de error. La UI maneja estos estados mostrando los resultados o proporcionando feedback al usuario en caso de fallos.
      
Este enfoque garantiza un flujo claro y predecible de datos e interacciones dentro de la aplicación, facilitando la escalabilidad y el mantenimiento del proyecto.

## Estructura del Proyecto
El proyecto de Free Market está organizado en módulos y paquetes bien definidos para promover la reutilización y separación de responsabilidades. A continuación, se detalla la estructura general:

1. **Módulos del Proyecto**:
    - `network`: Configura las conexiones de red utilizando Retrofit. Aquí se define la URL base, los interceptores, y los parámetros de tiempo de espera para las llamadas a la API.
    - `ui-components`: Contiene componentes de UI reutilizables que pueden ser utilizados en diferentes partes de la aplicación, facilitando la consistencia visual y funcional.
    - `app`: Módulo principal que gestiona los diferentes features de la aplicación, distribuidos en dominios específicos.

2. **Estructura de Features en el modulo Principal**(`app`) 
  Los features de la aplicación se organizan en dominios como `products` y  `search`, cada uno de los cuales sigue una estructura interna consistente para mantener la arquitectura limpia y modular. Por ejemplo, el dominio productos se organiza de la siguiente forma:
```plaintext
- products/
    - data/ 
    - presentation/ 
        - list/
        - detail/ 
    - ui/
        - navigation/
        - list/
        - detail/
    - di/
```
## Inyección de Dependencias
Para este proyecto, se implementó Koin como un desafío de aprendizaje, dado que es una herramienta que será muy útil para implementar Kotlin Multiplatform. Se destaca por su simplicidad y eficiencia en la inyección de dependencias. Actualmente, las dependencias se están generando de forma global.

Inicialmente, se intentó inyectar las dependencias utilizando scope para optimizar su manejo, asegurando que cada actividad gestionara y destruyera sus dependencias, de modo que solo estuvieran disponibles cuando fueran requeridas. Sin embargo, para acelerar el proceso, se optó por declarar las dependencias de forma global, dejando la implementación de scopes como una mejora a futuro.

Dentro de la carpeta `di` del dominio de cada feature, se pueden encontrar una serie de archivos titulados Module, que contienen la declaración de las dependencias necesarias para ese feature. En el caso de products hay tres módulos:
1. `ProductDataModule`: Maneja las dependencias de la capa de datos ya que para este feature existe un único repositorio que es compartido por los modulos de presentación de cada pantalla.
2. `ProductListModule`: Maneja las dependencias de la pantalla de lista de productos.
3. `ProductDetailModule`: Maneja las dependencias de la pantalla de detalle de producto.

## Herramientas
 - **_Kotlin_**: Lenguaje de programación utilizado para el desarrollo de la aplicación.
- **_Koin_**: Framework de inyección de dependencias que facilita la gestión de las dependencias en la aplicación.
- **_Retrofit_**: Biblioteca para la gestión de llamadas a la API y manejo de datos desde el servidor.
- **_Coroutines_**: Utilizadas para la programación asíncrona y la gestión de operaciones en segundo plano.
- **_Flow_**: Herramienta para el manejo de corrutinas y flujos de datos reactivos.
- **_Picasso_**: Biblioteca para el manejo y carga de imágenes.
- **_XML y ViewBinding_**: Utilizados para la construcción de la interfaz de usuario.
- **_Lottie_**: Biblioteca para la implementación de animaciones en la UI.
- **_Gson_**: Utilizado para la conversión de JSON a objetos de Kotlin.
- **_MockK_**: Biblioteca para crear mocks y realizar pruebas unitarias.
- **_JUnit 5_**: Framework para la realización de pruebas unitarias.
- **_Gradle_**: Herramienta de automatización utilizada para la construcción y gestión de dependencias del proyecto.
- **_Material Design_**: Conjunto de directrices para el diseño de la interfaz de usuario.

## Posibles Mejoras
1. **Implementación de Scopes en Koin**: Optimizar la inyección de dependencias utilizando scopes para un mejor manejo de dependencias utilizando el ciclo de vida de las actividades.
2. **Migración de la Carpeta Utils**: Considerar la migración de la carpeta utils de la app principal, que contiene extensiones y herramientas útiles para el desarrollo, a un módulo aparte para mejorar la modularidad del proyecto.
3. **Test Unitarios en network y utils**:  Implementar pruebas unitarias en los módulos de network y utils para asegurar el buen funcionamiento de estas herramientas y funcionalidades.
4. **Implementación de Tests de Integración y Tests Instrumentados**: Añadir pruebas de implementación y pruebas instrumentadas para verificar el comportamiento de la aplicación en un entorno más realista y asegurar que las funcionalidades estén correctamente integradas.
5. **Configuraciones de ProGuard**: Añadir configuraciones para ProGuard con el fin de ofuscar el código en la versión de release, mejorando la seguridad y reduciendo el tamaño del APK.
6. **Aumento del Coverage de Tests**: Aumentar la cobertura de pruebas en aquellas clases que aún no han sido testadas.
