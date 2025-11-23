#include <snes.h>

// auto-generated global instructions
extern char javasnes_patterns, javasnes_patterns_end;
extern char javasnes_map, javasnes_map_end;
extern char javasnes_palette, javasnes_palette_end;
// end auto-generated global instructions

extern char bgmap0, bgmap2, bgpalette4_end, paltijolos, bgmap1, bgtiles3_end, bgmap4, bgmap3, bgmap0_end, tilesfont, tilestijolos, bgtiles4_end, palfont, movebrrsound, bgtiles1, bgtiles0, bgtiles3, paltijolos_end, bgtiles2, bgpalette3_end, bgmap4_end, palpink_end, bgtiles4, losebrrsound_end, tilespink, tilestijolos_end, losebrrsound, bgtiles1_end, bgpalette2_end, bgmap3_end, bgpalette4, bgpalette3, bgpalette2, bgtiles0_end, bgpalette1, bgpalette0, movebrrsound_end, SOUNDBANK__, tilespink_end, bgtiles2_end, bgpalette0_end, bgmap1_end, palpink, bgpalette1_end, bgmap2_end;
u8 mudarBG = 0;
u16 pad0 = 0;
u8 atualBG = 4;
u32 HiScore = 10000;
u32 Score = 0;
s16 yTijolo = -1;
s16 xTijolo = -1;
s16 xPink = 0;
u8 qtdVidas = 3;
u8 colidiuTijolo = 0;
u8 ehParaCarregarPink = 0;
u8 ehParaCarregarTijolo = 0;
char animLeft[4] = {0, 4, 0, 8};
u8 animFrame = 0;
u8 noChao = 0;
#include "soundbank.h"

void printHiScore(void) {
	if ((atualBG == 4)) {
			consoleDrawText(2, 26, "HighScore: %d", (long long int) HiScore);
	}
	return;
}

void limparPrintHiScore(void) {
	consoleDrawText(1, 26, "                          ");
	return;
}

void comecarJogo(void) {
	spcProcess();
	if (((padsCurrent(0) & KEY_START) && (atualBG == 4))) {
			mudarBG = 1;
	}
	return;
}

void gameOver(void) {
	if ((atualBG == 3)) {
			spcPlaySound(0);
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			WaitForVBlank();
			mudarBG = 1;
	}
	return;
}

void printScoreGameOver(void) {
	if ((atualBG == 3)) {
			consoleDrawText(2, 26, "Score: %d", (long long int) Score);
	}
	spcPlaySound(0);
	return;
}

void atualizarHiScore(void) {
	if ((Score > HiScore)) {
			HiScore = Score;
			consoleCopySram((u8*)&HiScore, 4);
	}
	return;
}

void printDadosFase(void) {
	if ((atualBG <= 2)) {
			consoleDrawText(26, 3, "High");
			consoleDrawText(26, 4, "Score:");
			consoleDrawText(26, 6, "%06d", (long long int) HiScore);
			consoleDrawText(26, 10, "Score:");
			consoleDrawText(26, 12, "%06d", (long long int) Score);
			consoleDrawText(26, 16, "Level:");
			consoleDrawText(26, 18, "%d", (int) (4 - qtdVidas));
			consoleDrawText(26, 22, "Lifes:");
			consoleDrawText(26, 24, "%d", (int) qtdVidas);
	}
	return;
}

void atualizarLevel(void) {
	mudarBG = (yTijolo > 215) ? 1 : mudarBG;
	qtdVidas -= (yTijolo > 215) ? 1 : 0;
	return;
}

void atualizarScore(void) {
	Score += (((xTijolo < (xPink + 32)) && ((xTijolo + 8) > xPink)) && ((yTijolo < (192 + 32)) && ((yTijolo + 8) > 192))) ? (atualBG <= 2) ? 100 : 0 : 0;
	colidiuTijolo = (((xTijolo < (xPink + 32)) && ((xTijolo + 8) > xPink)) && ((yTijolo < (192 + 32)) && ((yTijolo + 8) > 192))) ? 1 : 0;
	return;
}

void carregarTijolo(void) {
	yTijolo = -16;
	xTijolo = rand() % 200;
	colidiuTijolo = 0;
	oamSetEx(1, OBJ_SMALL, (atualBG <= 2) ? OBJ_SHOW : OBJ_HIDE);
	return;
}

void atualizarTijolo(void) {
	yTijolo = ((snes_vblank_count % 3) == 0) ? (yTijolo + 1) : yTijolo;
	yTijolo = ((yTijolo > 215) || colidiuTijolo) ? -16 : yTijolo;
	xTijolo = ((yTijolo > 215) || colidiuTijolo) ? rand() % 200 : xTijolo;
	colidiuTijolo = ((yTijolo > 215) || colidiuTijolo) ? 0 : colidiuTijolo;
	return;
}

void EhParaCarregarPink(void) {
	ehParaCarregarPink = (atualBG <= 2) ? 1 : 0;
	return;
}

void atualizarPink(void) {
	xPink = (padsCurrent(0) & KEY_LEFT) ? (xPink + -1) : xPink;
	xPink = (padsCurrent(0) & KEY_RIGHT) ? (xPink + 1) : xPink;
	xPink = (xPink < -2) ? -2 : xPink;
	xPink = (xPink > 178) ? 178 : xPink;
	animFrame = ((padsCurrent(0) & KEY_LEFT) || (padsCurrent(0) & KEY_RIGHT)) ? (animFrame + 1) : animFrame;
	animFrame = (animFrame >= 4) ? 0 : animFrame;
	oamSetEx(0, OBJ_SMALL, (ehParaCarregarPink == 1) ? 0 : OBJ_SHOW);
	oamSet(0, xPink, 178, 3, 0, 0, animLeft[animFrame], 2);
	spcPlaySound(1);
	return;
}

void mudarBackground(void) {
	if ((mudarBG & mudarBG)) {
			mudarBG = 0;
			atualBG = (atualBG >= 4) ? 0 : (atualBG + 1);
			switch (atualBG) {
				case 0:
					limparPrintHiScore();
					setScreenOff();
					ehParaCarregarTijolo = 1;
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles0, &bgpalette0, 0, (&bgtiles0_end - &bgtiles0), (&bgpalette0_end - &bgpalette0), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap0, (&bgmap0_end - &bgmap0), SC_64x64, 0x1000);
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					setScreenOn();
					break;
				case 1:
					limparPrintHiScore();
					setScreenOff();
					ehParaCarregarTijolo = 1;
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles1, &bgpalette1, 0, (&bgtiles1_end - &bgtiles1), (&bgpalette1_end - &bgpalette1), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap1, (&bgmap1_end - &bgmap1), SC_64x64, 0x1000);
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					setScreenOn();
					break;
				case 2:
					limparPrintHiScore();
					setScreenOff();
					ehParaCarregarTijolo = 1;
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles2, &bgpalette2, 0, (&bgtiles2_end - &bgtiles2), (&bgpalette2_end - &bgpalette2), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap2, (&bgmap2_end - &bgmap2), SC_64x64, 0x1000);
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					setScreenOn();
					break;
				case 3:
					limparPrintHiScore();
					setScreenOff();
					ehParaCarregarPink = 0;
					ehParaCarregarTijolo = 0;
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles3, &bgpalette3, 0, (&bgtiles3_end - &bgtiles3), (&bgpalette3_end - &bgpalette3), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap3, (&bgmap3_end - &bgmap3), SC_64x64, 0x1000);
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					setScreenOn();
					break;
				case 4:
					limparPrintHiScore();
					Score = 0;
					qtdVidas = 3;
					yTijolo = -16;
					setScreenOff();
					ehParaCarregarPink = 0;
					ehParaCarregarTijolo = 0;
					WaitForVBlank();
					bgInitTileSet(1, &bgtiles4, &bgpalette4, 0, (&bgtiles4_end - &bgtiles4), (&bgpalette4_end - &bgpalette4), BG_16COLORS, 0x4000);
					bgInitMapSet(1, &bgmap4, (&bgmap4_end - &bgmap4), SC_64x64, 0x1000);
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					WaitForVBlank();
					setScreenOn();
					break;
		}
	}
	return;
}

void processor(void) {
	printHiScore();
	comecarJogo();
	gameOver();
	printScoreGameOver();
	atualizarHiScore();
	printDadosFase();
	atualizarLevel();
	atualizarScore();
	carregarTijolo();
	atualizarTijolo();
	EhParaCarregarPink();
	atualizarPink();
	mudarBackground();
	return;
}

int main(void) {
	
	spcBoot();
	
	spcAllocateSoundRegion(60);
	spcSetBank(&SOUNDBANK__);
	spcLoad(MOD_POLLEN8);
	spcPlay(0);
	brrsamples losesound;WaitForVBlank();
	brrsamples movesound;WaitForVBlank();
	spcSetSoundEntry(15, 15, 2, (&losebrrsound_end - &losebrrsound), &losebrrsound, &losesound);
	spcSetSoundEntry(15, 15, 5, (&movebrrsound_end - &movebrrsound), &movebrrsound, &movesound);
	
	bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
	bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
	
	
	setMode(BG_MODE1, 0);
	bgSetDisable(1);
	bgSetDisable(2);
	setScreenOn();
	
	consoleLoadSram((u8* )&HiScore, 4);
	HiScore = (HiScore % 100 == 0) ? HiScore : (10000);
	
	WaitForVBlank();
	
	u8 i;
	for (i = 0; i < 120; i++) {
		WaitForVBlank();
	}
	dmaClearVram();
	
	dmaClearVram();
	consoleSetTextGfxPtr(0x3000);
	consoleSetTextMapPtr(0x6800);
	consoleSetTextOffset(0x0100);
	consoleInitText(1, 32, &tilesfont, &palfont);
	setScreenOff();
	bgInitTileSet(1, &bgtiles4, &bgpalette4, 0, (&bgtiles4_end - &bgtiles4), (&bgpalette4_end - &bgpalette4), BG_16COLORS, 0x4000);
	bgInitMapSet(1, &bgmap4, (&bgmap4_end - &bgmap4), SC_64x64, 0x1000);
	bgSetGfxPtr(0, 0x2000);
	bgSetMapPtr(0, 0x6800, SC_32x32);
	setMode(BG_MODE1, 0);
	bgSetDisable(2);
	setScreenOn();
	WaitForVBlank();
	oamInitGfxSet(&tilespink, (&tilespink_end - &tilespink), &palpink, (&palpink_end - &palpink), 2, 0x6000, OBJ_SIZE32_L64);
	oamSet(0, 0, -16, 0, 0, 0, 0, 0);
	oamSetEx(0, OBJ_SMALL, OBJ_HIDE);
	oamInitGfxSet(&tilestijolos, (&tilestijolos_end - &tilestijolos), &paltijolos, (&paltijolos_end - &paltijolos), 3, 704, OBJ_SIZE8_L16);
	oamSet(1, 0, 192, 0, 0, 0, 0, 0);
	oamSetEx(1, OBJ_SMALL, OBJ_HIDE);
	while (1) {
		processor();
		WaitForVBlank();
	}
	return 0;
}
