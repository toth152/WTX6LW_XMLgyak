<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
    			<table border="1">
    			<h1>30000-nél drágábbak darabszáma</h1>
        		
           	 	<th>Ár>30000</th>
        
        		<tr>
            	<td><xsl:value-of select="count(//auto[ar>30000])"/></td>
        		</tr>
    			</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>