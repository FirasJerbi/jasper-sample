package tn.insat.Reports;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import tn.insat.domain.Compte;

/**
 * Created by My session on 11/05/2017.
 */
public class compteReportGenerator {
    public void generatePdfReport(List<Compte> comptes,Class c) throws JRException {

        String report = "src/main/webapp/report/account.jasper";
        InputStream jasperStream = c.getResourceAsStream(report);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(comptes);
        HashMap params = new HashMap();
        JasperPrint jprint = JasperFillManager.fillReport(jasperReport, params, ds);
        JasperExportManager.exportReportToPdfFile(jprint,"src/main/resources/reports.pdf");
    }
}
