package co.grandcircus.RideHard.ParkDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.grandcircus.RideHard.entity.ParkingSpot;

@Repository
@Transactional
public class ParkDao {

	@PersistenceContext
	EntityManager em;

	public void create(ParkingSpot parkingSpot) {
		em.persist(parkingSpot);

	}

	public List<ParkingSpot> findall() {
		return em.createQuery("From ParkingSpot", ParkingSpot.class).getResultList();
	}

//--------------will need to specify method to return more specific park results

}
