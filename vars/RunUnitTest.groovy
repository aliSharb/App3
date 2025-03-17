def call() {
    echo "Running Unit Tests..."

    // Navigate to the project root and execute Maven tests
    sh """
        cd ${env.WORKSPACE}
        mvn test
    """
}
