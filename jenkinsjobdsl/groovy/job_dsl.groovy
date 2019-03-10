import javaposse.jobdsl.dsl.DslFactory

// TODO this doesn't work !!!
DslFactory factory = this

factory.job("spring-transactions-demo-build") {
    deliveryPipelineConfiguration("Build")
    triggers { githubPush() }
//     scm {
//         github("vladflore/spring-transactions-demo.git")
//     }

    scm {
        git {
            remote {
                // TODO cannot connect to github !
                github('vladflore/spring-transactions-demo', 'ssh')
                credentials('mykey')
            }
        }
    }

    wrappers { colorizeOutput() }
    steps { shell("./mvnw clean install") }
    publishers {
        archiveJunit("**/target/surefire-reports/TEST-*.xml")
        archiveArtifacts("target/*.jar")
        downstreamParameterized {
            trigger("spring-transactions-demo-deploy") {
                triggerWithNoParameters()
            }
        }
    }
}

factory.job("spring-transactions-demo-deploy") {
    deliveryPipelineConfiguration("Deployment")
//    scm { github("vladflore/spring-transactions-demo.git") }

    scm {
        git {
            remote {
                github('vladflore/spring-transactions-demo', 'ssh')
                credentials('mykey')
            }
        }
    }

    steps { shell("echo 'Deploying artifact'") }
}

factory.deliveryPipelineView("spring-transactions-demo-view") {
    pipelines {
        component("Deployment", "spring-transactions-demo-build")
    }
    allowPipelineStart()
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}