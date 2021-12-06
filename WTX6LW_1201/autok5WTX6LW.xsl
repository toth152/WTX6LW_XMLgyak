<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		 <html>
            <body>
                <table border="1">
                <h1>Autótípusok és darabszámaik példányszám szerinti csökkenőben</h1>
             
                        <th>Márka</th>
                        <th>Darab</th>
                    
                    <tr>
                        <td>Fiat</td>
                        <td><xsl:value-of select="count(//autok/auto[tipus='Fiat'])"/></td>
                    </tr>
                    <tr>
                        <td>Skoda</td>
                        <td><xsl:value-of select="count(//autok/auto[tipus='Skoda'])"/></td>
                    </tr>
                    <tr>
                        <td>Opel</td>
                        <td><xsl:value-of select="count(//autok/auto[tipus='Opel'])"/></td>
                    </tr>
                </table>
            </body>
        </html>
	</xsl:template>
</xsl:stylesheet>