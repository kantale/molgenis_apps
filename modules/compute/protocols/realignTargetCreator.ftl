#
# =====================================================
# $Id$
# $URL$
# $LastChangedDate$
# $LastChangedRevision$
# $LastChangedBy$
# =====================================================
#

<#include "macros.ftl"/>
<@begin/>
#MOLGENIS walltime=35:59:00 mem=10

inputs "${dedupbam}" 
inputs "${indexfile}" 
inputs "${dbsnprod}"
inputs "${pilot1KgVcf}"
outputs "${realignTargets}"

java -Xmx10g -jar -Djava.io.tmpdir=${tempdir} \
${genomeAnalysisTKjar} \
-l INFO \
-T RealignerTargetCreator \
-U ALLOW_UNINDEXED_BAM \
-I ${dedupbam} \
-R ${indexfile} \
-D ${dbsnprod} \
-B:${pilot1KgVcf} \
-o ${realignTargets}
<@end />