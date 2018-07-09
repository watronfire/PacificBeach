package com.nate.pacificbeach;

import com.nate.pacificbeach.procedure.Procedure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProcedureLoader {
    public static Document loadProcedure( String location ) {
        File file = new File( location );
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document document = null;
        try {
            db = dbf.newDocumentBuilder();
            document = db.parse( file );
        } catch ( ParserConfigurationException e ) {
            e.printStackTrace();
            System.exit( 69 );
        } catch ( SAXException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        document.getDocumentElement().normalize();
        System.out.println( "Root element: " + document.getDocumentElement().getNodeName() );
        NodeList nList = document.getElementsByTagName( "Step" );
        for( int i = 0; i < nList.getLength(); i += 1 ) {
            Node nNode = nList.item( i );

            if( nNode.getNodeType() == Node.ELEMENT_NODE ) {
                Element elem = (Element) nNode;
                System.out.println( "Name: " + elem.getAttribute( "name" ) );
                System.out.println( "Duration: " + elem.getElementsByTagName( "time" ).item( 0 ).getTextContent() );
                System.out.println( "Description: " + elem.getElementsByTagName( "description" ).item( 0 ).getTextContent() );
            }
        }

        return document;
    }

    public static void loadBasicProcedure( String location ) {
        HashMap<String, String> traits = new HashMap<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader( new FileReader( location ) );

            String currentLine = br.readLine();
            while( currentLine != null ) {

                // Splits strings based on colons outside of quotes, allegedly. I don't speak regex.
                String[] cna = currentLine.split(":(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
                for( int i = 0; i < cna.length; i += 1 ) {
                    cna[i] = cna[i].trim().replace( "\"", "" );
                }

                traits.put( cna[0], cna[1] );
                currentLine = br.readLine();
            }

            Procedure proc = new Procedure( traits.get( "Name" ), traits.get( "Duration" ) );
            proc.setDescription( traits.get( "Description" ) );
            proc.reportProcedure();

        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }
}
