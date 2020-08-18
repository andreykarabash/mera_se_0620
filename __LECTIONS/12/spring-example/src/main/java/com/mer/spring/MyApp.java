package com.mer.spring;



import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.springframework.stereotype.Component;

import com.mer.spring.dto.Person;
import com.mer.spring.person.PersonService;

@Component
public class MyApp {

    PersonService personService;

    public MyApp(PersonService personService) {
        this.personService = personService;
    }


    @PostConstruct
    private void init() throws Exception {
        Server server = new Server(8090);

        server.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
                final String lastName = request.getParameter("lastName");
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html");

                if (lastName != null) {

                    final List<Person> byLastName = personService.findByLastName(lastName);
                    for (Person person : byLastName) {
                        response.getWriter().write("<b>" + person.getLastName() + "</b>, " + person.getName() + "<br/>");
                        response.setStatus(200);
                    }
                }
                baseRequest.setHandled(true);
            }
        });
        server.start();
       // server.join();
    }


}
