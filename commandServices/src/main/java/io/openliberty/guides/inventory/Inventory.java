package io.openliberty.guides.inventory;
import java.util.List;

import io.openliberty.guides.inventory.model.SystemData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
// tag::Inventory[]
public class Inventory {

    // tag::PersistenceContext[]
    @PersistenceContext(name = "jpa-unit")
    // end::PersistenceContext[]
    private EntityManager em;

    // tag::getSystems[]
    public List<SystemData> getSystems() {
        return em.createNamedQuery("SystemData.findAll", SystemData.class)
                .getResultList();
    }
    // end::getSystems[]

    // tag::getSystem[]
    public SystemData getSystem(String hostname) {
        // tag::find[]
        List<SystemData> systems =
                em.createNamedQuery("SystemData.findSystem", SystemData.class)
                        .setParameter("hostname", hostname)
                        .getResultList();
        return systems == null || systems.isEmpty() ? null : systems.get(0);
        // end::find[]
    }
    // end::getSystem[]

    // tag::add[]
    public void add(String hostname, String osName, String javaVersion, Long heapSize) {
        // tag::Persist[]
        em.persist(new SystemData(hostname, osName, javaVersion, heapSize));
        // end::Persist[]
    }
    // end::add[]

    // tag::update[]
    public void update(SystemData s) {
        // tag::Merge[]
        em.merge(s);
        // end::Merge[]
    }
    // end::update[]

    // tag::removeSystem[]
    public void removeSystem(SystemData s) {
        // tag::Remove[]
        em.remove(s);
        // end::Remove[]
    }
    // end::removeSystem[]

}
// end::Inventory[]