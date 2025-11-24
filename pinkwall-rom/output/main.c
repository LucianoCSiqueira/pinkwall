#include <snes.h>

// auto-generated global instructions
extern char javasnes_patterns, javasnes_patterns_end;
extern char javasnes_map, javasnes_map_end;
extern char javasnes_palette, javasnes_palette_end;
// end auto-generated global instructions

extern char bgmap0, bgmap2, bgpalette4_end, bgmap1, bgtiles3_end, bgmap4, bgmap3, bgmap0_end, tilesfont, bgtiles4_end, palfont, bgtiles1, bgtiles0, bgtiles3, bgtiles2, bgpalette3_end, bgmap4_end, palpink_end, bgtiles4, tilespink, bgtiles1_end, bgpalette2_end, bgmap3_end, bgpalette4, bgpalette3, bgpalette2, bgtiles0_end, bgpalette1, bgpalette0, SOUNDBANK__, tilespink_end, bgtiles2_end, bgpalette0_end, bgmap1_end, palpink, bgpalette1_end, bgmap2_end;
u8 mudarBG = 0;
u16 pad0 = 0;
u8 atualBG = 4;
u32 HiScore = 10000;
u32 Score = 0;
s16 yTijoloAnterior = -1;
s16 yTijolo = -1;
s16 xTijolo = -1;
s16 xPink = 0;
u8 qtdVidas = 3;
u8 ehParaCarregarPink = 0;
u8 ehParaCarregarTijolo = 0;
u8 tijoloEstaNaTela = 0;
char animLeft[4] = {0, 4, 0, 8};
u8 animFrame = 0;
u8 noChao = 0;
#include "soundbank.h"

void limparTela(void) {
	consoleDrawText(0, 0, "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ");
	return;
}

void printHiScore(void) {
	if ((atualBG == 4)) {
			consoleDrawText(2, 26, "HighScore: %d", (long long int) HiScore);
	}
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

void carregarTijolo(void) {
	if (((ehParaCarregarTijolo == 1) && (tijoloEstaNaTela == 0))) {
			u16 posXNovoTijolo = rand() % 23;
			yTijolo = 0;
			xTijolo = posXNovoTijolo;
			tijoloEstaNaTela = 1;
	}
	return;
}

void atualizarTijolo(void) {
	if ((tijoloEstaNaTela == 1)) {
			consoleDrawText(xTijolo, yTijoloAnterior, (yTijoloAnterior >= 0) ? "" : " ");
			yTijoloAnterior = yTijolo;
			yTijolo += 1;
			consoleDrawText(xTijolo, yTijolo, "#");
	}
	return;
}

void EhParaCarregarPink(void) {
	ehParaCarregarPink = (atualBG <= 2) ? 1 : 0;
	return;
}

void atualizarPink(void) {
	if ((ehParaCarregarPink == 1)) {
			xPink = (padsCurrent(0) & KEY_LEFT) ? (xPink + -1) : xPink;
			xPink = (padsCurrent(0) & KEY_RIGHT) ? (xPink + 3) : xPink;
			xPink = (xPink < -2) ? -2 : xPink;
			xPink = (xPink > 178) ? 178 : xPink;
			animFrame = ((padsCurrent(0) & KEY_LEFT) || (padsCurrent(0) & KEY_RIGHT)) ? (animFrame + 1) : animFrame;
			animFrame = (animFrame >= 4) ? 0 : animFrame;
			oamSetEx(0, OBJ_SMALL, (ehParaCarregarPink == 1) ? OBJ_HIDE : OBJ_SHOW);
			oamSet(0, xPink, 178, 3, 0, 0, animLeft[animFrame], 2);
			spcPlaySound(1);
	}
	return;
}

void mudarBackground(void) {
	if ((mudarBG & mudarBG)) {
			mudarBG = 0;
			atualBG = (atualBG >= 4) ? 0 : (atualBG + 1);
			switch (atualBG) {
				case 0:
					limparTela();
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
					limparTela();
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
					limparTela();
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
					limparTela();
					setScreenOff();
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
					limparTela();
					Score = 0;
					qtdVidas = 3;
					setScreenOff();
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
	spcSetModuleVolume(50);
	
	bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
	bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
	
	
	setMode(BG_MODE1, 0);
	bgSetDisable(1);
	bgSetDisable(2);
	setScreenOn();
	
	consoleLoadSram((u8* )&HiScore, 4);
	HiScore = ((HiScore % 100 == 0) && !((HiScore < 10000))) ? HiScore : (10000);
	
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
	while (1) {
		processor();
		WaitForVBlank();
	}
	return 0;
}
