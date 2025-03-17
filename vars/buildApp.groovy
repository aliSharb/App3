def call() {
    echo "Building the Java application..."

    // Navigate to the project root and build using Maven
    sh """
        cd ${env.WORKSPACE}
        mvn clean package
    """
}
