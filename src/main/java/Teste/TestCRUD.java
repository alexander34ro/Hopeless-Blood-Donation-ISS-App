package Teste;

import Persistence.DonatorEntity;
import Persistence.PacientEntity;
import Persistence.SpitalEntity;
import Services.DumbService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
public class TestCRUD {

    @Test
    public void testSpital() {
        DumbService dumbService = new DumbService();
        List<SpitalEntity> spitalEntities = dumbService.getAll(SpitalEntity.class);
        if (spitalEntities.size() > 0) {
            SpitalEntity spitalEntity = spitalEntities.get(spitalEntities.size() - 1);
            dumbService.delete(spitalEntity);
            List<SpitalEntity> spitalEntities1 = dumbService.getAll(SpitalEntity.class);
            assertEquals(spitalEntities.size(), spitalEntities1.size() + 1);
            dumbService.save(spitalEntity);
            List<SpitalEntity> spitalEntities2 = dumbService.getAll(SpitalEntity.class);
            assertEquals(spitalEntities.size(), spitalEntities2.size());
        }
    }
    @Test
    public void testPacient() {
        DumbService dumbService = new DumbService();
        List<PacientEntity> pacientEntities = dumbService.getAll(PacientEntity.class);
        if (pacientEntities.size() > 0) {
            PacientEntity pacientEntity = pacientEntities.get(pacientEntities.size() - 1);
            dumbService.delete(pacientEntity);
            List<PacientEntity> pacientEntities1 = dumbService.getAll(PacientEntity.class);
            assertEquals(pacientEntities.size(), pacientEntities1.size() + 1);
            dumbService.save(pacientEntity);
            List<PacientEntity> pacientEntities2 = dumbService.getAll(PacientEntity.class);
            assertEquals(pacientEntities.size(), pacientEntities2.size());
        }
    }
    @Test
    public void testDonator() {
        DumbService dumbService = new DumbService();
        List<DonatorEntity> donatorEntities = dumbService.getAll(DonatorEntity.class);
        if (donatorEntities.size() > 0) {
            DonatorEntity donatorEntity = donatorEntities.get(donatorEntities.size() - 1);
            dumbService.delete(donatorEntity);
            List<DonatorEntity> donatorEntities1= dumbService.getAll(DonatorEntity.class);
            assertEquals(donatorEntities.size(), donatorEntities1.size() + 1);
            dumbService.save(donatorEntities);
            List<DonatorEntity> donatorEntities2 = dumbService.getAll(DonatorEntity.class);
            assertEquals(donatorEntities.size(), donatorEntities2.size());
        }
    }
}
