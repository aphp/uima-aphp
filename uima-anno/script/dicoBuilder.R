#
# LOADING STEP
#
# LOAD FN
fname <- read.table("./dicoRawFname.csv",sep=",",header=FALSE,quote="",stringsAsFactor=FALSE)
fname <- tolower(fname$V1)
print(sprintf("fname: %s",length(fname)))
# LOAD LN
lname <- read.table("./dicoRawLname.csv",sep=",",header=FALSE,quote="",stringsAsFactor=FALSE)
lname <- tolower(lname$V1)
print(sprintf("lname: %s",length(lname)))

# LOAD MEDICAL
med <- read.table("./dicoRawMed.csv",sep=",",header=FALSE,quote="",stringsAsFactor=FALSE)
med <- tolower(med$V1)
print(sprintf("med: %s",length(med)))

# LOAD WORKERS
worker <- read.table("./dicoWorker.csv",sep=",",header=FALSE,quote="\"",stringsAsFactor=FALSE)
worker$V1 <- tolower(worker$V1)
worker$V2 <- tolower(worker$V2)
print(sprintf("workers: %s",nrow(worker)))

# LOAD SERVICE
serv <- read.table("./dicoServ.csv",sep=",",header=FALSE,quote="",stringsAsFactor=FALSE)
serv <- tolower(serv$V1)
print(sprintf("serv: %s",length(serv)))

# LOAD TRIGGER
trig <- read.table("./dicoRawNameTrigger.csv",sep=",",header=FALSE,quote="",stringsAsFactor=FALSE)
trig <- tolower(trig$V1)
print(sprintf("trig: %s",length(trig)))

## LOAD GENERAL
gen <- read.table("./dicoFinal.csv",sep="\t",header=TRUE,quote="\"",stringsAsFactor=FALSE)
print(sprintf("gen: %s",nrow(gen)))
genFname <- tolower(gen[gen$type%in%"PRENOM", c("mot")])
genOther <- tolower(gen[!tolower(gen$mot)%in%tolower(genFname), c("mot")])
print(sprintf("genFname: %s",length(genFname)))
print(sprintf("genOther: %s",length(genOther)))

genCommon <- tolower(unique(gen[!gen$type%in%c("PRENOM","VER"),"mot"]))
verb <- tolower(unique(gen[gen$type%in%c("VER"),"mot"]))
#
# PURIFICATION STEP
#

lworkerPurif <- unique(worker$V1)
#lworkerPurif <- lworkerPurif[!lworkerPurif%in%genOther]
print(sprintf("lworkerPurif: %s",length(lworkerPurif)))

fworkerPurif <- unique(worker$V2)
#fworkerPurif <- fworkerPurif[!fworkerPurif%in%genOther]
print(sprintf("fworkerPurif: %s",length(fworkerPurif)))

#fnamePurif <- fname[!fname%in%genOther&!fname%in%fworkerPurif]
fnamePurif <- fname[!fname%in%fworkerPurif]
print(sprintf("fnamePurif: %s",length(fnamePurif)))

#lnamePurif <- lname[!lname%in%genOther&!lname%in%lworkerPurif]
lnamePurif <- lname[!lname%in%lworkerPurif]
lnamePurif <- lnamePurif[!lnamePurif%in%fnamePurif]
print(sprintf("lnamePurif: %s",length(lnamePurif)))

medPurif <- med[!med%in%fnamePurif&!med%in%lnamePurif]
print(sprintf("medPurif: %s",length(medPurif)))

servPurif <- serv[!serv%in%lnamePurif&!serv%in%fnamePurif]
print(sprintf("servPurif: %s",length(servPurif)))


#
# GENERATION STEP
#
formatXml <- function(str){ return(sprintf("<entry><key>%s</key></entry>", str))}
writeXml <- function(file, str, normCase, annot, multiWord, special="false"){ 
	spliter <- ''
	if(multiWord%in%"true"){
	spliter <- 'multiWordSeparator=" "'
	}
	if(special%in%"true"){
	spliter <- 'multiWordSeparator="special"'
	}
	top <- sprintf(header, normCase,  multiWord, spliter, annot)
	last <- foot
	str <- c(top,str,foot)
	write.table(file=file,x=str,sep=",",row.names=FALSE,col.names=FALSE,quote=FALSE) }
fnamePurif <- formatXml(fnamePurif)
lnamePurif <- formatXml(lnamePurif)
fworkerPurif <- formatXml(fworkerPurif)
lworkerPurif <- formatXml(lworkerPurif)
medPurif <- formatXml(medPurif)
servPurif <- formatXml(servPurif)
trigPurif <- formatXml(trig)
verbPurif <- formatXml(verb)
commonPurif <- formatXml(genCommon)
workerPurif <- formatXml(paste0(worker$V1,"|",worker$V2))

header <- '<?xml version="1.0" encoding="UTF-8"?><dictionary xmlns="http://incubator.apache.org/uima" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="dictionary.xsd">
<typeCollection>
<dictionaryMetaData 
	caseNormalization="%s" 
	multiWordEntries="%s"
	%s
	/>
<languageId>en</languageId>
<typeDescription>
<typeName>fr.aphp.wind.uima.type.%s</typeName>
</typeDescription>
<entries>'
foot <- '</entries>
</typeCollection>
</dictionary>'


writeXml("fnamePurif.xml",fnamePurif,"true","GeneralFirstName","false")
writeXml("lnamePurif.xml",lnamePurif,"true","GeneralLastName","false")
writeXml("medPurif.xml",medPurif,"true","GeneralMedicalTerm","false")
writeXml("servPurif.xml",servPurif,"true","GeneralService","false")
writeXml("trigPurif.xml",trigPurif,"true","GeneralNameTrigger","false")
writeXml("lworkerPurif.xml",lworkerPurif,"true","PhysicianLastName","true")
writeXml("fworkerPurif.xml",fworkerPurif,"true","PhysicianFirstName","true")
writeXml("commonPurif.xml",commonPurif,"true","GeneralCommon","false")
writeXml("verbPurif.xml",verbPurif,"true","GeneralVerb","false","true")
