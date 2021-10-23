#!/usr/bin/env groovy
import com.builders.Docker

def call(String sourceImageName, String destImageName) {
    return new Docker(this).tag(sourceImageName, destImageName)
}