CREATE DATABASE  IF NOT EXISTS `tfg` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tfg`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: tfg
-- ------------------------------------------------------
-- Server version	5.5.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `password` varchar(512) NOT NULL,
  `salt` varchar(512) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('c3700948b0b3157d0a96d371a63b8f3a7c7ee2927c0fb34f9ce705086d8cdd5d4a40918d8080c8e4edfb1702fe8835d87e1ac7291ad23c8f5155ec1564e74fbb','4edb9f115da6154b2a3eabd61087d8cbdc5352e26c2d3d486430c02a1afe0754dc4ab763cf95350990d9701b5efecefd2200ea14fe54fb0d2137aaecc818e8c1',1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `pais` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nacimiento` date DEFAULT NULL,
  `nombre` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sal` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `about` mediumtext COLLATE utf8_unicode_ci,
  `imagen` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'euskal herria','2015-04-29','alberto','c3700948b0b3157d0a96d371a63b8f3a7c7ee2927c0fb34f9ce705086d8cdd5d4a40918d8080c8e4edfb1702fe8835d87e1ac7291ad23c8f5155ec1564e74fbb','4edb9f115da6154b2a3eabd61087d8cbdc5352e26c2d3d486430c02a1afe0754dc4ab763cf95350990d9701b5efecefd2200ea14fe54fb0d2137aaecc818e8c1','prueba\r\nde\r\nmodificación','imagenes/prueba.jpg','alberto@alberto.com'),(2,'EEUU','1992-01-01','duke','2dd9a76fff89d444961ef124a9d1ed3ed6bb103504ead99a27c8c22ecd9b0416a93d1208b77d2168ff13b257c109f36e399106e5b9fa24387766ed7f79f96137','da12c7c1ef2d55407d0d6ec4608586cec478cce235edc592fe61ac0877dc18fcbb8a0bcbb88eed8658b9f5a26de0aac94c4d5259419d6a6221fa65ae2e1f8a77','Mascota de java','imagenes/1433320932997.png','duke@duke.com'),(4,'francia','2040-05-05','feldespato','5ed6ae7304a52725592ca479f9c97303910bf81a423e4c5841fcaab658f4e494d356dd9b8d9d17cd48617a995b807990f2174a564f146b96e45f06041ca1269e','7e3385e4ff19ff950d8632304e4abf25d73027aa15114f20a410ab2b0dacc1102e96de6dab280761f52f533f37014d963cc1983aa709d7e5ea3c2a549f4667b6','						adfafd\r\n					','imagenes/1434800843487.png','a@a.com');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capitulo`
--

DROP TABLE IF EXISTS `capitulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capitulo` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `obra` int(6) NOT NULL DEFAULT '0',
  `nombre` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `texto` mediumtext COLLATE utf8_unicode_ci,
  `comentarios_autor` mediumtext COLLATE utf8_unicode_ci,
  `fecha_comentario` date DEFAULT NULL,
  `imagen` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`obra`),
  KEY `obra` (`obra`),
  CONSTRAINT `capitulo_ibfk_1` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capitulo`
--

LOCK TABLES `capitulo` WRITE;
/*!40000 ALTER TABLE `capitulo` DISABLE KEYS */;
INSERT INTO `capitulo` VALUES (37,17,'Historia','Java se creó como una herramienta de programación para ser usada en un proyecto de set-top-box en una pequeña operación denominada the Green Project en Sun Microsystems en el año 1991. El equipo (Green Team), compuesto por trece personas y dirigido por James Gosling, trabajó durante 18 meses en Sand Hill Road en Menlo Park en su desarrollo.\r\n,\r\n,El lenguaje se denominó inicialmente Oak (por un roble que había fuera de la oficina de Gosling), luego pasó a denominarse Green tras descubrir que Oak era ya una marca comercial registrada para adaptadores de tarjetas gráficas y finalmente se renombró a Java.\r\n,\r\n,Es frecuentada por algunos de los miembros del equipo. Pero no está claro si es un acrónimo o no, aunque algunas fuentes señalan que podría tratarse de las iniciales de sus diseñadores: James Gosling, Arthur Van Hoff, y Andy Bechtolsheim. Otros abogan por el siguiente acrónimo, Just Another Vague Acronym (\"sólo otro acrónimo ambiguo más\"). La hipótesis que más fuerza tiene es la de que Java debe su nombre a un tipo de café disponible en la cafetería cercana, de ahí que el icono de java sea una taza de café caliente. Un pequeño signo que da fuerza a esta teoría es que los 4 primeros bytes (el número mágico) de los archivos.class que genera el compilador, son en hexadecimal, 0xCAFEBABE. A pesar de todas estas teorías, el nombre fue sacado al parecer de una lista aleatoria de palabras.3\r\n,\r\n,Los objetivos de Gosling eran implementar una máquina virtual y un lenguaje con una estructura y sintaxis similar a C++. Entre junio y julio de 1994, tras una sesión maratoniana de tres días entre John Gaga, James Gosling, Patrick Naughton, Wayne Rosing y Eric Schmidt, el equipo reorientó la plataforma hacia la Web. Sintieron que la llegada del navegador web Mosaic, propiciaría que Internet se convirtiese en un medio interactivo, como el que pensaban era la televisión por cable. Naughton creó entonces un prototipo de navegador, WebRunner, que más tarde sería conocido como HotJava.\r\n,\r\n,En 1994, se les hizo una demostración de HotJava y la plataforma Java a los ejecutivos de Sun. Java 1.0a pudo descargarse por primera vez en 1994, pero hubo que esperar al 23 de mayo de 1995, durante las conferencias de SunWorld, a que vieran la luz pública Java y HotJava, el navegador Web. El acontecimiento fue anunciado por John Gage, el Director Científico de Sun Microsystems. El acto estuvo acompañado por una pequeña sorpresa adicional, el anuncio por parte de Marc Andreessen, Vicepresidente Ejecutivo de Netscape, de que Java sería soportado en sus navegadores. El 9 de enero del año siguiente, 1996, Sun fundó el grupo empresarial JavaSoft para que se encargase del desarrollo tecnológico. [1] Dos semanas más tarde la primera versión de Java fue publicada.\r\n,\r\n,La promesa inicial de Gosling era Write Once, Run Anywhere (Escríbelo una vez, ejecútalo en cualquier lugar), proporcionando un lenguaje independiente de la plataforma y un entorno de ejecución (la JVM) ligero y gratuito para las plataformas más populares de forma que los binarios (bytecode) de las aplicaciones Java pudiesen ejecutarse en cualquier plataforma.\r\n,\r\n,El entorno de ejecución era relativamente seguro y los principales navegadores web pronto incorporaron la posibilidad de ejecutar applets Java incrustadas en las páginas web.\r\n,\r\n,Java ha experimentado numerosos cambios desde la versión primigenia, JDK 1.0, así como un enorme incremento en el número de clases y paquetes que componen la biblioteca estándar.4\r\n,\r\n,Desde J2SE 1.4, la evolución del lenguaje ha sido regulada por el JCP (Java Community Process), que usa Java Specification Requests (JSRs) para proponer y especificar cambios en la plataforma Java. El lenguaje en sí mismo está especificado en la Java Language Specification (JLS), o Especificación del Lenguaje Java. Los cambios en los JLS son gestionados en JSR 901.\r\n,JDK 1.0 (23 de enero de 1996) — Primer lanzamiento: comunicado de prensa\r\n,JDK 1.1 (19 de febrero de 1997) — Principales adiciones incluidas: comunicado de prensa una reestructuración intensiva del modelo de eventos AWT (Abstract Windowing Toolkit)\r\n,clases internas (inner classes)\r\n,JavaBeans\r\n,JDBC (Java Database Connectivity), para la integración de bases de datos\r\n,RMI (Remote Method Invocation)\r\n,\r\n,J2SE 1.2 (8 de diciembre de 1998) — Nombre clave Playground. Esta y las siguientes versiones fueron recogidas bajo la denominación Java 2 y el nombre \"J2SE\" (Java 2 Platform, Standard Edition), reemplazó a JDK para distinguir la plataforma base de J2EE (Java 2 Platform, Enterprise Edition) y J2ME (Java 2 Platform, Micro Edition). Otras mejoras añadidas incluían: comunicado de prensa la palabra reservada (keyword) strictfp\r\n,reflexión en la programación\r\n,la API gráfica ( Swing) fue integrada en las clases básicas\r\n,la máquina virtual (JVM) de Sun fue equipada con un compilador JIT (Just in Time) por primera vez\r\n,Java Plug-in\r\n,Java IDL, una implementación de IDL (Lenguaje de Descripción de Interfaz) para la interoperabilidad con CORBA\r\n,Colecciones (Collections)\r\n,\r\n,J2SE 1.3 (8 de mayo de 2000) — Nombre clave Kestrel. Los cambios más notables fueron:comunicado de prensa lista completa de cambios la inclusión de la máquina virtual de HotSpot JVM (la JVM de HotSpot fue lanzada inicialmente en abril de 1999, para la JVM de J2SE 1.2)\r\n,RMI fue cambiado para que se basara en CORBA\r\n,JavaSound\r\n,se incluyó el Java Naming and Directory Interface (JNDI) en el paquete de bibliotecas principales (anteriormente disponible como una extensión)\r\n,Java Platform Debugger Architecture (JPDA)\r\n,\r\n,J2SE 1.4 (6 de febrero de 2002) — Nombre Clave Merlin. Este fue el primer lanzamiento de la plataforma Java desarrollado bajo el Proceso de la Comunidad Java como JSR 59. Los cambios más notables fueron: comunicado de prensalista completa de cambios Palabra reservada assert (Especificado en JSR 41.)\r\n,Expresiones regulares modeladas al estilo de las expresiones regulares Perl\r\n,Encadenación de excepciones Permite a una excepción encapsular la excepción de bajo nivel original.\r\n,non-blocking NIO (New Input/Output) (Especificado en JSR 51.)\r\n,Logging API (Specified in JSR 47.)\r\n,API I/O para la lectura y escritura de imágenes en formatos como JPEG o PNG\r\n,Parser XML integrado y procesador XSLT (JAXP) (Especificado en JSR 5 y JSR 63.)\r\n,Seguridad integrada y extensiones criptográficas (JCE, JSSE, JAAS)\r\n,Java Web Start incluido (El primer lanzamiento ocurrió en marzo de 2001 para J2SE 1.3) (Especificado en JSR 56.)\r\n,\r\n,J2SE 5.0 (30 de septiembre de 2004) — Nombre clave: Tiger. (Originalmente numerado 1.5, esta notación aún es usada internamente.[2]) Desarrollado bajo JSR 176, Tiger añadió un número significativo de nuevas características comunicado de prensa Plantillas (genéricos) — provee conversión de tipos (type safety) en tiempo de compilación para colecciones y elimina la necesidad de la mayoría de conversión de tipos (type casting). (Especificado por JSR 14.)\r\n,Metadatos — también llamados anotaciones, permite a estructuras del lenguaje como las clases o los métodos, ser etiquetados con datos adicionales, que puedan ser procesados posteriormente por utilidades de proceso de metadatos. (Especificado por JSR 175.)\r\n,Autoboxing/unboxing — Conversiones automáticas entre tipos primitivos (Como los int) y clases de envoltura primitivas (Como Integer). (Especificado por JSR 201.)\r\n,Enumeraciones — la palabra reservada enum crea una typesafe, lista ordenada de valores (como Dia.LUNES, Dia.MARTES, etc.). Anteriormente, esto solo podía ser llevado a cabo por constantes enteras o clases construidas manualmente (enum pattern). (Especificado por JSR 201.)\r\n,Varargs (número de argumentos variable) — El último parámetro de un método puede ser declarado con el nombre del tipo seguido por tres puntos (e.g. void drawtext(String... lines)). En la llamada al método, puede usarse cualquier número de parámetros de ese tipo, que serán almacenados en un array para pasarlos al método.\r\n,Bucle for mejorado — La sintaxis para el bucle for se ha extendido con una sintaxis especial para iterar sobre cada miembro de un array o sobre cualquier clase que implemente Iterable, como la clase estándar Collection, de la siguiente forma:\r\n,\r\n,Este ejemplo itera sobre el objeto Iterable widgets, asignando, en orden, cada uno de los elementos a la variable w, y llamando al método display() de cada uno de ellos. (Especificado por JSR 201.)\r\n,Java SE 6 (11 de diciembre de 2006) — Nombre clave Mustang. Estuvo en desarrollo bajo la JSR 270. En esta versión, Sun cambió el nombre \"J2SE\" por Java SE y eliminó el \".0\" del número de versión.[3]. Está disponible en http://java.sun.com/javase/6/. Los cambios más importantes introducidos en esta versión son: Incluye un nuevo marco de trabajo y APIs que hacen posible la combinación de Java con lenguajes dinámicos como PHP, Python, Ruby y JavaScript.\r\n,Incluye el motor Rhino, de Mozilla, una implementación de Javascript en Java.\r\n,Incluye un cliente completo de Servicios Web y soporta las últimas especificaciones para Servicios Web, como JAX-WS 2.0, JAXB 2.0, STAX y JAXP.\r\n,Mejoras en la interfaz gráfica y en el rendimiento.\r\n,\r\n,Java SE 7 — Nombre clave Dolphin. En el año 2006 aún se encontraba en las primeras etapas de planificación. Su lanzamiento fue en julio de 2011. Soporte para XML dentro del propio lenguaje.\r\n,Un nuevo concepto de superpaquete.\r\n,Soporte para closures.\r\n,Introducción de anotaciones estándar para detectar fallos en el software.\r\n,\r\n,No oficiales: NIO2.\r\n,Java Module System.\r\n,Java Kernel.\r\n,Nueva API para el manejo de Días y Fechas, la cual reemplazara las antiguas clases Date y Calendar.\r\n,Posibilidad de operar con clases BigDecimal usando operandos.\r\n,\r\n,Java SE 8 — lanzada en marzo de 2014. Cabe destacar: Incorpora de forma completa la librería JavaFX.\r\n,Diferentes mejoras en seguridad.\r\n,Diferentes mejoras en concurrencia.\r\n,Añade funcionalidad para programación funcional mediante expresiones Lambda.\r\n,Mejora la integración de JavaScript.\r\n,Nuevas API para manejo de fechas y tiempo (date - time).\r\n,\r\n,\r\n,En el 2005 se calcula en 4,5 millones el número de desarrolladores y 2.500 millones de dispositivos habilitados con tecnología Java.\r\n','Esta copiado de la wikpedia','2015-06-03',NULL),(38,17,'Filosofía','El lenguaje Java se creó con cinco objetivos principales:\r\n1.Debería usar el paradigma de la programación orientada a objetos.\r\n2.Debería permitir la ejecución de un mismo programa en múltiples sistemas operativos.\r\n3.Debería incluir por defecto soporte para trabajo en red.\r\n4.Debería diseñarse para ejecutar código en sistemas remotos de forma segura.\r\n5.Debería ser fácil de usar y tomar lo mejor de otros lenguajes orientados a objetos, como C++.\r\n\r\nPara conseguir la ejecución de código remoto y el soporte de red, los programadores de Java a veces recurren a extensiones como CORBA (Common Object Request Broker Architecture), Internet Communications Engine o OSGi respectivamente.\r\n\r\nOrientado a objetos[editar]\r\n\r\nLa primera característica, orientado a objetos (“OO”), se refiere a un método de programación y al diseño del lenguaje. Aunque hay muchas interpretaciones para OO, una primera idea es diseñar el software de forma que los distintos tipos de datos que usen estén unidos a sus operaciones. Así, los datos y el código (funciones o métodos) se combinan en entidades llamadas objetos. Un objeto puede verse como un paquete que contiene el “comportamiento” (el código) y el “estado” (datos). El principio es separar aquello que cambia de las cosas que permanecen inalterables. Frecuentemente, cambiar una estructura de datos implica un cambio en el código que opera sobre los mismos, o viceversa. Esta separación en objetos coherentes e independientes ofrece una base más estable para el diseño de un sistema software. El objetivo es hacer que grandes proyectos sean fáciles de gestionar y manejar, mejorando como consecuencia su calidad y reduciendo el número de proyectos fallidos. Otra de las grandes promesas de la programación orientada a objetos es la creación de entidades más genéricas (objetos) que permitan la reutilización del software entre proyectos, una de las premisas fundamentales de la Ingeniería del Software. Un objeto genérico “cliente”, por ejemplo, debería en teoría tener el mismo conjunto de comportamiento en diferentes proyectos, sobre todo cuando estos coinciden en cierta medida, algo que suele suceder en las grandes organizaciones. En este sentido, los objetos podrían verse como piezas reutilizables que pueden emplearse en múltiples proyectos distintos, posibilitando así a la industria del software a construir proyectos de envergadura empleando componentes ya existentes y de comprobada calidad; conduciendo esto finalmente a una reducción drástica del tiempo de desarrollo. Podemos usar como ejemplo de objeto el aluminio. Una vez definidos datos (peso, maleabilidad, etc.), y su “comportamiento” (soldar dos piezas, etc.), el objeto “aluminio” puede ser reutilizado en el campo de la construcción, del automóvil, de la aviación, etc.\r\n\r\nLa reutilización del software ha experimentado resultados dispares, encontrando dos dificultades principales: el diseño de objetos realmente genéricos es pobremente comprendido, y falta una metodología para la amplia comunicación de oportunidades de reutilización. Algunas comunidades de “código abierto” (open source) quieren ayudar en este problema dando medios a los desarrolladores para diseminar la información sobre el uso y versatilidad de objetos reutilizables y bibliotecas de objetos.\r\n\r\nIndependencia de la plataforma[editar]\r\n\r\nLa segunda característica, la independencia de la plataforma, significa que programas escritos en el lenguaje Java pueden ejecutarse igualmente en cualquier tipo de hardware. Este es el significado de ser capaz de escribir un programa una vez y que pueda ejecutarse en cualquier dispositivo, tal como reza el axioma de Java, \"write once, run anywhere\".\r\n\r\nPara ello, se compila el código fuente escrito en lenguaje Java, para generar un código conocido como “bytecode” (específicamente Java bytecode)—instrucciones máquina simplificadas específicas de la plataforma Java. Esta pieza está “a medio camino” entre el código fuente y el código máquina que entiende el dispositivo destino. El bytecode es ejecutado entonces en la máquina virtual (JVM), un programa escrito en código nativo de la plataforma destino (que es el que entiende su hardware), que interpreta y ejecuta el código. Además, se suministran bibliotecas adicionales para acceder a las características de cada dispositivo (como los gráficos, ejecución mediante hebras o threads, la interfaz de red) de forma unificada. Se debe tener presente que, aunque hay una etapa explícita de compilación, el bytecode generado es interpretado o convertido a instrucciones máquina del código nativo por el compilador JIT (Just In Time).\r\n\r\nHay implementaciones del compilador de Java que convierten el código fuente directamente en código objeto nativo, como GCJ. Esto elimina la etapa intermedia donde se genera el bytecode, pero la salida de este tipo de compiladores sólo puede ejecutarse en un tipo de arquitectura.\r\n\r\nLa licencia sobre Java de Sun insiste que todas las implementaciones sean “compatibles”. Esto dio lugar a una disputa legal entre Microsoft y Sun, cuando éste último alegó que la implementación de Microsoft no daba soporte a las interfaces RMI y JNI además de haber añadido características ‘’dependientes’’ de su plataforma. Sun demandó a Microsoft y ganó por daños y perjuicios (unos 20 millones de dólares) así como una orden judicial forzando la acatación de la licencia de Sun. Como respuesta, Microsoft no ofrece Java con su versión de sistema operativo, y en recientes versiones de Windows, su navegador Internet Explorer no admite la ejecución de applets sin un conector (o plugin) aparte. Sin embargo, Sun y otras fuentes ofrecen versiones gratuitas para distintas versiones de Windows.\r\n\r\nLas primeras implementaciones del lenguaje usaban una máquina virtual interpretada para conseguir la portabilidad. Sin embargo, el resultado eran programas que se ejecutaban comparativamente más lentos que aquellos escritos en C o C++. Esto hizo que Java se ganase una reputación de lento en rendimiento. Las implementaciones recientes de la JVM dan lugar a programas que se ejecutan considerablemente más rápido que las versiones antiguas, empleando diversas técnicas, aunque sigue siendo mucho más lento que otros lenguajes.\r\n\r\nLa primera de estas técnicas es simplemente compilar directamente en código nativo como hacen los compiladores tradicionales, eliminando la etapa del bytecode. Esto da lugar a un gran rendimiento en la ejecución, pero tapa el camino a la portabilidad. Otra técnica, conocida como compilación JIT (Just In Time, o \"compilación al vuelo\"), convierte el bytecode a código nativo cuando se ejecuta la aplicación. Otras máquinas virtuales más sofisticadas usan una \"recompilación dinámica\" en la que la VM es capaz de analizar el comportamiento del programa en ejecución y recompila y optimiza las partes críticas. La recompilación dinámica puede lograr mayor grado de optimización que la compilación tradicional (o estática), ya que puede basar su trabajo en el conocimiento que de primera mano tiene sobre el entorno de ejecución y el conjunto de clases cargadas en memoria. La compilación JIT y la recompilación dinámica permiten a los programas Java aprovechar la velocidad de ejecución del código nativo sin por ello perder la ventaja de la portabilidad en ambos.\r\n\r\nLa portabilidad es técnicamente difícil de lograr, y el éxito de Java en ese campo ha sido dispar. Aunque es de hecho posible escribir programas para la plataforma Java que actúen de forma correcta en múltiples plataformas de distinta arquitectura, el gran número de estas con pequeños errores o inconsistencias llevan a que a veces se parodie el eslogan de Sun, \"Write once, run anywhere\" como \"Write once, debug everywhere\" (o “Escríbelo una vez, ejecútalo en cualquier parte” por “Escríbelo una vez, depúralo en todas partes”).\r\n\r\nEl concepto de independencia de la plataforma de Java cuenta, sin embargo, con un gran éxito en las aplicaciones en el entorno del servidor, como los Servicios Web, los Servlets, los Java Beans, así como en sistemas empotrados basados en OSGi, usando entornos Java empotrados.\r\n\r\nEl recolector de basura[editar]\r\n\r\nVéase también: Recolector de basura\r\n\r\nEn Java el problema fugas de memoria se evita en gran medida gracias a la recolección de basura (o automatic garbage collector). El programador determina cuándo se crean los objetos y el entorno en tiempo de ejecución de Java (Java runtime) es el responsable de gestionar el ciclo de vida de los objetos. El programa, u otros objetos pueden tener localizado un objeto mediante una referencia a éste. Cuando no quedan referencias a un objeto, el recolector de basura de Java borra el objeto, liberando así la memoria que ocupaba previniendo posibles fugas (ejemplo: un objeto creado y únicamente usado dentro de un método sólo tiene entidad dentro de éste; al salir del método el objeto es eliminado). Aun así, es posible que se produzcan fugas de memoria si el código almacena referencias a objetos que ya no son necesarios—es decir, pueden aún ocurrir, pero en un nivel conceptual superior. En definitiva, el recolector de basura de Java permite una fácil creación y eliminación de objetos y mayor seguridad.\r\n','Esto también esta copiado de la Wikipedia, del mismo articulo que antes.','2015-06-03',NULL),(39,17,'Sintaxis','La sintaxis de Java se deriva en gran medida de C++. Pero a diferencia de éste, que combina la sintaxis para programación genérica, estructurada y orientada a objetos, Java fue construido desde el principio para ser completamente orientado a objetos. Todo en Java es un objeto (salvo algunas excepciones), y todo en Java reside en alguna clase (recordemos que una clase es un molde a partir del cual pueden crearse varios objetos).\r\n\r\nAplicaciones autónomas[editar]\r\n\r\nEste ejemplo necesita una pequeña explicación.\r\nTodo en Java está dentro de una clase, incluyendo programas autónomos.\r\nEl código fuente se guarda en archivos con el mismo nombre que la clase que contienen y con extensión “.java”. Una clase (class) declarada pública (public) debe seguir este convenio. En el ejemplo anterior, la clase es Hola, por lo que el código fuente debe guardarse en el fichero “Hola.java”\r\nEl compilador genera un archivo de clase (con extensión “.class”) por cada una de las clases definidas en el archivo fuente. Una clase anónima se trata como si su nombre fuera la concatenación del nombre de la clase que la encierra, el símbolo “$”, y un número entero.\r\nLos programas que se ejecutan de forma independiente y autónoma, deben contener el método ”main()”.\r\nLa palabra reservada ”void” indica que el método main no devuelve nada.\r\nEl método main debe aceptar un array de objetos tipo String. Por acuerdo se referencia como ”args”, aunque puede emplearse cualquier otro identificador.\r\nLa palabra reservada ”static” indica que el método es un método de clase, asociado a la clase en vez de a una instancia de la misma. El método main debe ser estático o ’’de clase’’.\r\nLa palabra reservada public significa que un método puede ser llamado desde otras clases, o que la clase puede ser usada por clases fuera de la jerarquía de la propia clase. Otros tipos de acceso son ”private” o ”protected”.\r\nLa utilidad de impresión (en pantalla por ejemplo) forma parte de la biblioteca estándar de Java: la clase ‘’’System’’’ define un campo público estático llamado ‘’’out’’’. El objeto out es una instancia de ‘’’PrintStream’’’, que ofrece el método ‘’’println (String)’’’ para volcar datos en la pantalla (la salida estándar).\r\nLas aplicaciones autónomas se ejecutan dando al entorno de ejecución de Java el nombre de la clase cuyo método main debe invocarse. Por ejemplo, una línea de comando (en Unix o Windows) de la forma java –cp . Hola ejecutará el programa del ejemplo (previamente compilado y generado “Hola.class”). El nombre de la clase cuyo método main se llama puede especificarse también en el fichero “MANIFEST” del archivo de empaquetamiento de Java (.jar).\r\n\r\nApplets[editar]\r\n\r\nLas applet Java son programas incrustados en otras aplicaciones, normalmente una página Web que se muestra en un navegador.\r\n\r\nActualmente HTML 5 ha eliminado el uso de la etiqueta <applet>. Pero todavía existe la forma de usarlo en HTML5. (Texto en inglés) Java Applets in HTML5.\r\n\r\nLa sentencia import indica al compilador de Java que incluya las clases java.applet. Applet y java.awt. Graphics, para poder referenciarlas por sus nombres, sin tener que anteponer la ruta completa cada vez que se quieran usar en el código fuente.\r\n\r\nLa clase Hola extiende (extends) a la clase Applet, es decir, es una subclase de ésta. La clase Applet permite a la aplicación mostrar y controlar el estado del applet. La clase Applet es un componente del AWT (Abstract Window Toolkit), que permite al applet mostrar una interfaz gráfica de usuario o GUI (Graphical User Interface), y responder a eventos generados por el usuario.\r\n\r\nLa clase Hola sobrecarga el método paint (Graphics) heredado de la superclase contenedora (Applet en este caso), para acceder al código encargado de dibujar. El método paint() recibe un objeto Graphics que contiene el contexto gráfico para dibujar el applet. El método paint() llama al método drawString (String, int, int) del objeto [4]\r\n\r\nServlets[editar]\r\n\r\nArtículo principal: Java Servlet\r\n\r\nLos servlets son componentes de la parte del servidor de Java EE, encargados de generar respuestas a las peticiones recibidas de los clientes.\r\n\r\nLas sentencias import indican al compilador de Java la inclusión de todas las clases públicas e interfaces de los paquetes java.io y javax.servlet en la compilación.\r\n\r\nLa clase Hola extiende (extends), es heredera de la clase GenericServlet. Esta clase proporciona la interfaz para que el servidor le pase las peticiones al servlet y el mecanismo para controlar el ciclo de vida del servlet.\r\n\r\nLa clase Hola sobrecarga el método service (ServletRequest, ServletResponse), definido por la interfaz servlet para acceder al manejador de la petición de servicio. El método service() recibe un objeto de tipo ServletRequest que contiene la petición del cliente y un objeto de tipo ServletResponse, usado para generar la respuesta que se devuelve al cliente. El método service() puede lanzar (throws) excepciones de tipo ServletException e IOException si ocurre algún tipo de anomalía.\r\n\r\nEl método setContentType (String) en el objeto respuesta establece el tipo de contenido MIME a \"text/html\", para indicar al cliente que la respuesta a su petición es una página con formato HTML. El método getWriter() del objeto respuesta devuelve un objeto de tipo PrintWriter, usado como una tubería por la que viajarán los datos al cliente. El método println (String) escribe la cadena \"Hola, mundo!\" en la respuesta y finalmente se llama al método close() para cerrar la conexión, que hace que los datos escritos en la tubería o stream sean devueltos al cliente.\r\n\r\nAplicaciones con ventanas[editar]\r\n\r\nSwing es la biblioteca para la interfaz gráfica de usuario avanzada de la plataforma Java SE.\r\nLas instrucciones import indican al compilador de Java que las clases e interfaces del paquete javax.swing se incluyan en la compilación.\r\n\r\nLa clase Hola extiende (extends) la clase javax.swing.JFrame, que implementa una ventana con una barra de título y un control para cerrarla.\r\n\r\nEl constructor Hola() inicializa el marco o frame llamando al método setDefaultCloseOperation (int) heredado de JFrame para establecer las operaciones por defecto cuando el control de cierre en la barra de título es seleccionado al valor WindowConstants.DISPOSE_ON_CLOSE. Esto hace que se liberen los recursos tomados por la ventana cuando es cerrada, y no simplemente ocultada, lo que permite a la máquina virtual y al programa acabar su ejecución. A continuación se crea un objeto de tipo JLabel con el texto \"Hola, mundo!\", y se añade al marco mediante el método add (Component), heredado de la clase Container. El método pack(), heredado de la clase Window, es invocado para dimensionar la ventana y distribuir su contenido.\r\n\r\nEl método main() es llamado por la JVM al comienzo del programa. Crea una instancia de la clase Hola y hace la ventana sea mostrada invocando al método setVisible (boolean) de la superclase (clase de la que hereda) con el parámetro a true. Véase que, una vez el marco es dibujado, el programa no termina cuando se sale del método main(), ya que el código del que depende se encuentra en un hilo de ejecución independiente ya lanzado, y que permanecerá activo hasta que todas las ventanas hayan sido destruidas.\r\n','También esta copiado:\r\nhttp://es.wikipedia.org/wiki/Java_(lenguaje_de_programaci%C3%B3n)#Historia','2015-06-03',NULL),(40,18,'Un capitulo','asdf','asd','2015-06-20','imagenes/1434799535485.jpg'),(41,18,'otro captul','afdsklñfasddfaf','','2015-06-20','imagenes/1434799561841.jpg'),(42,18,'Ultimo capitulo de prueba para eliminar capitulo','asdfasfdaf','dasfdaf','2015-06-24',NULL);
/*!40000 ALTER TABLE `capitulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
  `autor` int(6) NOT NULL DEFAULT '0',
  `obra` int(6) NOT NULL DEFAULT '0',
  `capitulo` int(6) NOT NULL DEFAULT '0',
  `comentario` int(6) NOT NULL AUTO_INCREMENT,
  `texto` mediumtext COLLATE utf8_unicode_ci,
  `fecha_comentario` datetime DEFAULT NULL,
  PRIMARY KEY (`comentario`,`autor`,`obra`,`capitulo`),
  KEY `autor` (`autor`),
  KEY `obra` (`obra`),
  KEY `capitulo` (`capitulo`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_3` FOREIGN KEY (`capitulo`) REFERENCES `capitulo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1,18,40,1,'hla','2015-06-23 19:21:56'),(1,18,40,2,'quetal','2015-06-23 19:22:00'),(1,18,40,3,'viejo','2015-06-23 19:22:03'),(1,18,41,4,'jaujaufdaf adf','2015-06-23 19:22:13');
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obra`
--

DROP TABLE IF EXISTS `obra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obra` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `autor` int(6) NOT NULL DEFAULT '0',
  `titulo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resumen` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_in` date DEFAULT NULL,
  `fecha_mod` date DEFAULT NULL,
  `portada` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`autor`),
  UNIQUE KEY `titulo_UNIQUE` (`titulo`),
  KEY `autor` (`autor`),
  CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra`
--

LOCK TABLES `obra` WRITE;
/*!40000 ALTER TABLE `obra` DISABLE KEYS */;
INSERT INTO `obra` VALUES (17,2,'Java','Java es un lenguaje de programación de propósito general, concurrente, orientado a objetos que fue diseñado específicamente para tener tan pocas dependencias de implementación como fuera posible. Su intención es permitir que los desarrolladores de aplicaciones escriban el programa una vez y lo ejecuten en cualquier dispositivo (conocido en inglés como WORA, o \"write once, run anywhere\"), lo que quiere decir que el código que es ejecutado en una plataforma no tiene que ser recompilado para correr en otra. Ja','2015-06-03','2015-06-03','imagenes/1433321594005.png'),(18,1,'Nueva obra ','Se va a borrar','2015-06-20','2015-06-24','imagenes/1434799535454.jpg');
/*!40000 ALTER TABLE `obra` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-25 17:16:47
