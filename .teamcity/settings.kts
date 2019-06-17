import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.1"

project {

    vcsRoot(Foobar12)

    template(Xxx1)

    subProject(SubTW)
}

object Xxx1 : Template({
    name = "xxx"

    steps {
        script {
            name = "from template"
            id = "RUNNER_11"
            scriptContent = "echo template"
        }
    }
})

object Foobar12 : GitVcsRoot({
    name = "https://github.com/innayan/foobar"
    url = "https://github.com/innayan/foobar"
})


object SubTW : Project({
    name = "SubTW"

    buildType(SubTW_ConfigForSubtw)
})

object SubTW_ConfigForSubtw : BuildType({
    templates(Xxx1)
    name = "config for subtw"

    vcs {
        root(Foobar12)
    }
})
