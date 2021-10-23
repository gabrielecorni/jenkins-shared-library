#!/usr/bin/env groovy
import com.builders.Docker

def call(String imageName, String dockerfilePath = ".", Map buildArgs = [:]) {
    return new Docker(this).build(imageName, dockerfilePath, buildArgs)
}