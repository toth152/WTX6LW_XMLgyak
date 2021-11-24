<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		 <html> 
         <body> 
            <h2>Toth Tibor David apple- template</h2> 
            <xsl:apply-templates select = "class/student" /> 
         </body> 
      </html> 
	</xsl:template>
	
	<xsl:template match = "class/student"> 
      <xsl:apply-templates select = "@id" /> 
      <xsl:apply-templates select = "vezeteknev" /> 
      <xsl:apply-templates select = "keresztnev" /> 
      <xsl:apply-templates select = "becenev" /> 
      <xsl:apply-templates select = "kor" />
      <xsl:apply-templates select = "fizetes" />  
      <br /> 
   </xsl:template>  

   <xsl:template match = "@id"> 
      ID: <span style = "font-size = 25px;"> 
         <xsl:value-of select = "." /> 
      </span> 
      <br /> 
   </xsl:template>    

    <xsl:template match = "vezeteknev"> 
      Vezeteknev: <span style = "color:ForestGreen;"> 
         <xsl:value-of select = "." /> 
      </span> 
      <br /> 
   </xsl:template> 
   
   <xsl:template match = "keresztnev"> 
      Keresztnev: <span style = "color:FireBrick;"> 
         <xsl:value-of select = "." /> 
      </span> 
      <br /> 
   </xsl:template>

   <xsl:template match = "becenev"> 
      <span style = "color:black;"> 
         <xsl:value-of select = "." /> 
      </span> 
       
   </xsl:template>  

   <xsl:template match = "kor"> 
      Kor: <span style = "color:MediumBlue;"> 
         <xsl:value-of select = "." /> 
      </span> 
      <br /> 
   </xsl:template> 
   
   <xsl:template match = "fizetes"> 
      Fizetes: <span style = "color:Crimson;"> 
         <xsl:value-of select = "." /> 
      </span> 
      <br /> 
   </xsl:template> 
	
</xsl:stylesheet>