#!/usr/bin/env groovy
import com.builders.Docker

def call(String registry, String credentials) {
    return new Docker(this).login(registry, credentials)
}