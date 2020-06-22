package com.capgroup.digital.pdftemplate.infrastructure.itext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.capgroup.digital.pdftemplate.ThymeleafConfig;
import com.capgroup.digital.pdftemplate.domain.mockdata.PriceItForm;
import com.capgroup.digital.pdftemplate.domain.mockdata.PriceItResult;
import com.capgroup.digital.pdftemplate.domain.mockdata.TestData;
import com.capgroup.digital.pdftemplate.domain.model.Report;
import com.capgroup.digital.pdftemplate.domain.model.Format;
import com.capgroup.digital.pdftemplate.domain.model.Position;
import com.capgroup.digital.pdftemplate.domain.model.element.Element;
import com.capgroup.digital.pdftemplate.domain.model.element.RunningElement;
import com.capgroup.digital.pdftemplate.domain.model.element.RunningElementType;
import com.capgroup.digital.pdftemplate.domain.service.ReportService;
import com.google.gson.Gson;
import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.font.FontProvider;
//import com.itextpdf.licensekey.LicenseKey;

@Service
public class ItextPdfService implements ReportService {
	
    @Autowired
    private ThymeleafConfig config;
	
    private static final String PAGENUMBER = "pageNumber";
    
 	private ConverterProperties properties = new ConverterProperties();
 	private Context context = new Context();
 	private PdfPageFormCopier copier = new PdfPageFormCopier();
 	
 	public static void main(String args[]) {
 		 String input = "{clientName:Test,planType:pptpa,planAssets:20000,estimatedAnnContributions:50000,wilShireService:NA,isEDJ:false,numParticipants:2,shareClassTypesR2:R-2,shareClassTypesR2E:R-2E,shareClassTypesR3:R-3,shareClassTypesR4:R-4,shareClassTypesR5E:R-5E,shareClassTypesR5:R-5,shareClassTypesR6:null,tpaFee:12000,adviserFee:null,isDollarAdviserFee:null,mShareClassType:null,mileStone:null,mr2:R-2,mr2e:R-2E,mr3:R-3,mr4:R-4,mr5e:R-5E,mr5:R-5,mr6:null,includeMileStone:true,includeResult:true,calculateCost:null,clientVersion:CreatePDF,previous:null,fileName:price-it-tool-report.pdf,pdfPluginInstalled:false,priceBand1:null,priceBand2:null,priceBand3:null,priceBand4:null,priceBand5:null,priceBand6:null,priceBand7:null,priceBand8:null,milestonePriceBand1:null,milestonePriceBand2:null,milestonePriceBand3:null,milestonePriceBand4:null,milestonePriceBand5:null,milestonePriceBand6:null,milestonePriceBand7:null,milestonePriceBand8:null,adviserFeeGVA:null,isDollarAdviserFeeGVA:null,isCompanyHeadQuarterInNewYork:null,adviserHighLowFee:null,creditR2:null,creditR2e:null,creditR3:null,creditR4:null,creditR5:0,creditR5e:0,creditR6:0,r2Fpo:0.65,r2eFpo:0.5,r3Fpo:0.35,r4Fpo:0.25,r5eFpo:0,r5po:0,r6po:null,page:0}";         
         Gson gson = new Gson();
         PriceItForm form = gson.fromJson(input, PriceItForm.class);
       /*  System.out.println(form.getClientName());
         System.out.println(form.getPlanType());
         System.out.println(form.getPlanAssets());*/
         
         String result = "{isRestricted:false,shareClassType:R-2,oneTimeFee:0.0,annualFee:2400.0,baseFee:0.0,perParticipantFee:0.0,annualRKDFee:0.0,wilShireService:0.0,wilShireFeePercent:0.0,wilShireFeeDollar:0.0,planCredit:-280.0,planCreditPercent:0.4,netCredit:2120.0,annualTPAFee:12000.0,investAvgExpRatio:1.49,investDollarCost:1043.0,adviserFeePercent:0.0,adviserFeeDollar:0.0,planCostPct:21.66,planCostDollar:15163.0,dealerCompPct:0.65,dealerCompDollar:455.0,feeBasedCompPct:0.65,feeBasedCompDollar:455.0,creditAmountPct:0.0,creditAmountDollar:0.0}";
         PriceItResult res = gson.fromJson(result, PriceItResult.class);
         System.out.println(res.getAnnualFee());
 	}
 	
 	@Override
	public byte[] testPDF() throws IOException {
		
		properties.setBaseUri(getResources());
        String[] samplePages = {"mileStoneCover","mileStone1","mileStone2","R2milestone1","R2milestone2",
        		"R2Emilestone1","R2Emilestone2","R3milestone1","R3milestone2","R4milestone1","R4milestone2",
        		"R5Emilestone1","R5Emilestone2","R5milestone1","R5milestone2"};
               
        Map<String, Object> data = new HashMap<String, Object>();
       
        data = TestData.getPriceItFormData(data);
        data = TestData.getPriceItResultData(data);
        data = TestData.getMileStoneData(data);
             
        context.setVariables(data);
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
    	PdfWriter writer = new PdfWriter("C:\\personal\\project\\cg\\priceit2.pdf");
    	//PdfWriter writer = new PdfWriter("C:\\Users\\inyrakc\\Desktop\\priceit1.pdf");
    	PdfDocument pdf = new PdfDocument(writer);
    	
    	combinePages(pdf, samplePages, context, Format.STANDARD);
    	
    	
    	Element pagination = new Element();
    	pagination.setFontSize(8);
    	pagination.setPosition(Position.RIGHT);
    	pagination.setText("Page");
    	RunningElement footer = new RunningElement();
    	footer.setPagination(pagination);
    	
    	Element title = new Element();
    	title.setFontSize(8);
    	title.setPosition(Position.RIGHT);
    	title.setText("Test");
    	RunningElement header = new RunningElement();
    	header.setTitle(title);
    	
    	PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
    	
    	
    	try(Document doc = new Document(pdf)) {
    		doc.setFont(font);
    		addRunningElement(pdf, doc, RunningElementType.HEADER, header);
    		addRunningElement(pdf, doc, RunningElementType.FOOTER, footer);
    		
    	}
    	
    	
    	pdf.close();
    	return output.toByteArray();
	}
	
 	@Override
	public byte[] createPDF(Report data, Format format, List<RunningElementType> elements) throws IOException {
		
		loadItext7License();
		
		properties.setBaseUri(getResources());
        context.setVariables(data.getData());
        String[] pages = data.getTemplates();
    	
        ByteArrayOutputStream output = new ByteArrayOutputStream();
    	PdfWriter writer = new PdfWriter(output);
    	PdfDocument pdf = new PdfDocument(writer);
    	
    	combinePages(pdf, pages, context, format);
    	
    	try(Document doc = new Document(pdf)) {
	    	if(elements.contains(RunningElementType.HEADER)) {
	    		addRunningElement(pdf, doc, RunningElementType.HEADER, data.getHeader());
	    	}
	    	if(elements.contains(RunningElementType.FOOTER)) {
	    		addRunningElement(pdf, doc, RunningElementType.FOOTER, data.getFooter());
	    	}
    	}
    	
    	pdf.close();
        return output.toByteArray();
        
    }
    
	private void combinePages(PdfDocument parent, String[] pages, Context variables, Format format) throws IOException {
		int count = 1;
    	for (String html : pages) {
    		
    		variables.setVariable(PAGENUMBER, count);
    		String processedHtml = config.templateEngine().process(html, variables);
    		
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		PdfDocument temp = new PdfDocument(new PdfWriter(baos));
    		
    		count++;
    		
    		if(format != null && format.equals(Format.LANDSCAPE)) {
    			temp.setDefaultPageSize(PageSize.A4.rotate());
    		}
    		
    		String FONTS = "src/main/resources/static/fonts/arial/";
    		FontProvider fontProvider = new DefaultFontProvider();
    	    fontProvider.addDirectory(FONTS);
    	    properties.setFontProvider(fontProvider);
    		
			HtmlConverter.convertToPdf(processedHtml, temp, properties);
			
            temp = new PdfDocument(new PdfReader(new ByteArrayInputStream(baos.toByteArray())));
            temp.copyPagesTo(1, temp.getNumberOfPages(), parent, copier);          
            temp.close();
    	}
	}
	
	private void addRunningElement(PdfDocument pdf, Document doc, RunningElementType runningElementType, RunningElement runningElement) {
        if(runningElementType.equals(RunningElementType.HEADER)) {
        	pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderEvent(doc, runningElement));
        }
        if(runningElementType.equals(RunningElementType.FOOTER)) {
        	pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEvent(doc, runningElement));
        }
	}
	
    private String getResources() {
    	URL url = getClass().getResource("/static/");
    	return url.toString();
    }
	
    private void loadItext7License() throws IOException {
    	URL url = getClass().getResource("/itextkeys/");
    	Path path = Paths.get(URI.create(url.toString())); 
    	try (Stream<Path> paths = Files.walk(path)) {
    		List<String> result = paths.filter(Files::isRegularFile)
    				.map(Path::toString).collect(Collectors.toList());
    		String[] keys = new String[result.size()];
    		for(int i = 0; i < result.size(); i++) {
    			keys[i] = result.get(i);
    		}
        	//LicenseKey.loadLicenseFile(keys);
    	} 
    }

}
