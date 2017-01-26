package demo

import grails.web.mapping.LinkGenerator

import static grails.async.Promises.waitAll

class DemoController {

    LinkGenerator grailsLinkGenerator

    def index() {
        String link = '<unknown>'
        def task = Person.async.task {
            link = grailsLinkGenerator.link(controller: 'demo', action: 'foo', params: [name: 'Jeff'], absolute: true)
        }

        waitAll(task)

        render "Link: ${link}"
    }

    def foo(String name) {
        render "Name: $name"
    }
}
