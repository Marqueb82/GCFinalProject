package co.grandcircus.RideHard.ParkDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.grandcircus.RideHard.ParkWhizApi.Park;

@Repository
@Transactional
public class ParkDao {

	@PersistenceContext
	EntityManager em;

	public void create(Park parkingSpot) {
		em.persist(parkingSpot);

	}

	public List<Park> findall() {
		return em.createQuery("From Park", Park.class).getResultList();
	}

//--------------will need to specify method to return more specific park results

}
