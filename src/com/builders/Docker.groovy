#!/usr/bin/env groovy

package com.builders

class Docker implements Serializable{

    def script

    Docker(script) {
        this.script = script
    }

    def isNullOrEmpty(String s) {
        return !s?.trim()
    }

    def serializeMap(String flag, Map args = [:]) {
        return args.collect {"$flag $it.key=\"$it.value\""}.join(' ')
    }

    def build(String imageName, String dockerfilePath = ".", Map buildArgs = [:]) {
        String serializedBuildArgs = serializeMap("--build-arg", buildArgs)

        script.echo "Building $imageName..."
        script.sh "docker build -f $dockerfilePath -t $imageName $serializedBuildArgs ."
        script.echo "Success"
    }

    def login(String registry, String credentials) {
        script.withCredentials([script.usernamePassword(credentialsId: credentials, passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.echo "Logging into $registry/$script.USER..."
            script.sh "echo $script.PASS | docker login --username $script.USER --password-stdin $registry"
            script.echo "Success"
        }
    }

    def tag(String sourceImageName, String destImageName) {
        script.echo "Tagging $sourceImageName to $destImageName..."
        script.sh "docker tag $sourceImageName $destImageName"
        script.echo "Success"
    }

    def push(String imageName) {
        script.echo "Pushing $imageName..."
        script.sh "docker push $imageName"
        script.echo "Success"
    }

    def rmi(String imageName) {
        script.echo "Cleaning $imageName..."
        script.sh "docker rmi -f $imageName"
        script.echo "Success"
    }
}