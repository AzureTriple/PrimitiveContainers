plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

java {withSourcesJar()}

tasks {
    test {useJUnitPlatform()}
    withType<Jar> {
        manifest {attributes(mapOf("PrimitiveContainer" to rootProject.name))}
        archiveBaseName.set(rootProject.name)
    }
}

dependencies {
    // Use JUnit test framework.
    implementation("org.junit.jupiter:junit-jupiter:5.9.1")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:30.1.1-jre")

    // Test utils
    implementation(files("../libs/JavaAssertionUtil.jar"))
}