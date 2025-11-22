.include "hdr.asm"

.section ".rodata1" superfree
javasnes_patterns:
.incbin "javasnes_logo.pic"

javasnes_patterns_end:


javasnes_map:
.incbin "javasnes_logo.map"

javasnes_map_end:


javasnes_palette:
.incbin "javasnes_logo.pal"

javasnes_palette_end:

.ends

.section ".rodata2" superfree
tilesfont:
.incbin "fonte.pic"

palfont:
.incbin "fonte.pal"

tilespink:
.incbin "sprites.pic"
tilespink_end:

palpink:
.incbin "sprites.pal"
palpink_end:

tilestijolos:
.incbin "tijolo.pic"
tilestijolos_end:

paltijolos:
.incbin "tijolo.pal"
paltijolos_end:


.ends

.section ".rodata3" superfree
bgtiles0:
.incbin "BG_1.pic"
bgtiles0_end:

bgpalette0:
.incbin "BG_1.pal"
bgpalette0_end:

bgmap0:
.incbin "BG_1.map"
bgmap0_end:


.ends

.section ".rodata4" superfree
bgtiles1:
.incbin "BG_2.pic"
bgtiles1_end:

bgpalette1:
.incbin "BG_2.pal"
bgpalette1_end:

bgmap1:
.incbin "BG_2.map"
bgmap1_end:


.ends

.section ".rodata5" superfree
bgtiles2:
.incbin "BG_3.pic"
bgtiles2_end:

bgpalette2:
.incbin "BG_3.pal"
bgpalette2_end:

bgmap2:
.incbin "BG_3.map"
bgmap2_end:


.ends

.section ".rodata6" superfree
bgtiles3:
.incbin "BG_4.pic"
bgtiles3_end:

bgpalette3:
.incbin "BG_4.pal"
bgpalette3_end:

bgmap3:
.incbin "BG_4.map"
bgmap3_end:


.ends

.section ".rodata7" superfree
bgtiles4:
.incbin "BG_5.pic"
bgtiles4_end:

bgpalette4:
.incbin "BG_5.pal"
bgpalette4_end:

bgmap4:
.incbin "BG_5.map"
bgmap4_end:


.ends

.section ".rodata8" superfree
losebrrsound:
.incbin "lose.brr"
losebrrsound_end:

movebrrsound:
.incbin "move.brr"
movebrrsound_end:


.ends
