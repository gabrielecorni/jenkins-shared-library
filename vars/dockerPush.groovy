#!/usr/bin/env groovy
import com.builders.Docker

def call(String imageName) {
    return new Docker(this).push(imageName)
}