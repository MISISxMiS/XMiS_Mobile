package com.example.xmis_project.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.hypot
import kotlin.math.max

@Composable
fun SkewedCutParallelogramShape(
    skew: Dp,            // положительное => наклон вправо
    cutTopLeft: Dp,        // срез левый-верх (длина по краю)
    cutTopRight: Dp,       // срез правый-верх
    cutBottomRight: Dp,     // срез правый-низ
): Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val skewPxRaw = with(density) { skew.toPx() }
        val cutTLraw = with(density) { cutTopLeft.toPx() }
        val cutTRraw = with(density) { cutTopRight.toPx() }
        val cutBRraw = with(density) { cutBottomRight.toPx() }

        // ограничиваем наклон, чтобы не уйти за половину ширины
        val maxSkew = size.width / 2f
        val skewPx = skewPxRaw.coerceIn(-maxSkew, maxSkew)

        // основные углы параллелограмма (A - top-left, B - top-right, C - bottom-right, D - bottom-left)
        val A = Offset(skewPx, 0f)
        val B = Offset(size.width, 0f)
        val C = Offset(size.width - skewPx, size.height)
        val D = Offset(0f, size.height)

        // длины рёбер (топ/боттом — горизонтальные)
        val topEdgeLen = max(0f, B.x - A.x)          // = size.width - skewPx
        val bottomEdgeLen = topEdgeLen
        // длина правого/левого наклонного ребра
        val sideEdgeLen = hypot((C.x - B.x), (C.y - B.y)) // sqrt(skew^2 + height^2)

        // clamp срезов так, чтобы не пересечься
        var cutTL = cutTLraw.coerceAtLeast(0f)
        var cutTR = cutTRraw.coerceAtLeast(0f)
        var cutBR = cutBRraw.coerceAtLeast(0f)

        // верхняя грань: cutTL + cutTR <= topEdgeLen
        if (cutTL + cutTR > topEdgeLen && topEdgeLen > 0f) {
            val scale = topEdgeLen / (cutTL + cutTR)
            cutTL *= scale
            cutTR *= scale
        }

        // правая грань: cutTR + cutBR <= sideEdgeLen
        if (cutTR + cutBR > sideEdgeLen && sideEdgeLen > 0f) {
            val scale = sideEdgeLen / (cutTR + cutBR)
            cutTR *= scale
            cutBR *= scale
        }

        // bottom cutBR не должен превышать bottomEdgeLen
        if (cutBR > bottomEdgeLen) cutBR = bottomEdgeLen

        // направления (unit vectors)
        val topDir = if (topEdgeLen > 0f) Offset(1f, 0f) else Offset.Zero
        // направление по правой стороне (из B в C)
        val rightDx = C.x - B.x
        val rightDy = C.y - B.y
        val rightLen = hypot(rightDx, rightDy).coerceAtLeast(0.0001f)
        val rightDir = Offset(rightDx / rightLen, rightDy / rightLen)
        // направление по левой стороне (из A в D) — оно такое же по направлению
        val leftDx = D.x - A.x
        val leftDy = D.y - A.y
        val leftLen = hypot(leftDx, leftDy).coerceAtLeast(0.0001f)
        val leftDir = Offset(leftDx / leftLen, leftDy / leftLen)

        // compute cut points (точки по краям)
        val A_top = Offset(A.x + topDir.x * cutTL, A.y + topDir.y * cutTL)               // на верхней грани, справа от A
        val A_left = Offset(A.x + leftDir.x * cutTL, A.y + leftDir.y * cutTL)           // на левой грани, вниз от A

        val B_top = Offset(B.x - topDir.x * cutTR, B.y - topDir.y * cutTR)              // на верхней грани, слева от B
        val B_right = Offset(B.x + rightDir.x * cutTR, B.y + rightDir.y * cutTR)       // на правой грани, вниз от B

        val C_right = Offset(C.x - rightDir.x * cutBR, C.y - rightDir.y * cutBR)       // на правой грани, вверх от C
        val C_bottom = Offset(C.x - cutBR, C.y)                                        // на нижней грани, влево от C

        val path = Path().apply {
            moveTo(A_top.x, A_top.y)
            lineTo(B_top.x, B_top.y)
            lineTo(B_right.x, B_right.y)
            lineTo(C_right.x, C_right.y)
            lineTo(C_bottom.x, C_bottom.y)
            lineTo(D.x, D.y)
            lineTo(A_left.x, A_left.y)
            close()
        }

        return Outline.Generic(path)
    }
}
