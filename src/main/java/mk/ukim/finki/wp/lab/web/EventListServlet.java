package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = "/servlet/events")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchEvents = req.getParameter("searchEvents");
        String searchCategories = req.getParameter("searchCategory");
        String minPopularityScore = req.getParameter("minPopularityScore");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);


        if (searchEvents == null || searchEvents.isEmpty() && minPopularityScore == null || minPopularityScore.isEmpty() && searchCategories == null || searchCategories.isEmpty()) {
            context.setVariable("events", eventService.findAll());
            springTemplateEngine.process("listEvents.html", context, resp.getWriter());

        } else {
            List<Event> eventList;
            eventList = eventService.searchEvents(searchEvents, Double.parseDouble(minPopularityScore), searchCategories);
            context.setVariable("events", eventList);
            springTemplateEngine.process("listEvents.html", context, resp.getWriter());
        }
    }



}
