def call(String KUBE_CONFIG_PATH, String KUBE_NAMESPACE, String KUBE_DEPLOYMENT_FILE, String BUILD_TAG) {
    script {
        sh "sed -i 's/TAG_PLACEHOLDER/${BUILD_TAG}/g' ${KUBE_DEPLOYMENT_FILE}"
        sh "kubectl --kubeconfig=${KUBE_CONFIG_PATH} apply -n ${KUBE_NAMESPACE} -f ${KUBE_DEPLOYMENT_FILE}"
        sh "kubectl --kubeconfig=${KUBE_CONFIG_PATH} rollout status deployment my-app-deployment -n ${KUBE_NAMESPACE}"
    }
}
