<div align="center">

# Animate visibility

<img src="https://cdn-icons-png.flaticon.com/512/12431/12431059.png" width="100" height="100"/>
  
</div><br>

<div>
  <p>
    Para adicionar efeitos de animação às interfaces do usuário, o Jetpack Compose inclui a API Animation. A API Animation consiste em classes e funções que fornecem uma ampla variedade de opções de animação que podem ser 
    adicionadas aos seus aplicativos. 
  </p>
  <br>

  ## Animating Visibility
  <p>
    Talvez a forma mais simples de animação envolva animar a aparência e o desaparecimento de um elemento <b>composable.</b> Em vez de um componente aparecer e desaparecer instantaneamente, 
    vários efeitos animados podem ser aplicados usando o elemento <b>composable AnimatedVisibility.</b> Por exemplo, os elementos da interface do usuário podem ser feitos 
    para aparecer e desaparecer gradualmente da vista, deslizar para dentro e para fora da posição horizontal ou verticalmente, ou mostrar e ocultar expandindo e encolhendo.
    O requisito mínimo para chamar <b>AnimatedVisibility</b> é um parâmetro de variável do tipo <i>Boolean</i> para controlar se seus elementos filhos <i>Composable</i> são visíveis ou não. 
  </p>
  <p>
    Neste projeto, o elemento <i>composable</i> recebe um texto a ser exibido no botão e um manipulador <i>onClick</i> e o novo valor de estado a ser passado ao manipulador quando o botão é clicado. 
    O botão também aceita uma cor de fundo opcional cujo padrão é azul.
  </p>
</div>

```kotlin
@Composable
fun CustomButton (
  text: String,
  targetState: Boolean,
  onClick: (Boolean) -> unit,
  bgColor: Color = Color.Blue) {

    Button (
      onClick = { onClick(targetState) },
      colors = ButtonDefaults.buttonColors(
        containerColor = bgColor,
        contentColor = Color.White
      )
  ) {
      Text(text)
    }
}
```

<div>
  <p>
    O objetivo do manipulador é alterar o estado visível da caixa com base na seleção do botão.
    Os elementos <i>Composables</i> de <b>Column</b> e <b>Row</b> são usados ​​para exibir dois elementos <i>Composables</i> <b>customButton</b> e <b>box</b>. Os botões recebem o texto a ser exibido, 
    a nova configuração para o estado <b>boxVisible</b> e uma referência ao manipulador <b>onClick.</b> Quando um botão é clicado, ele chama o manipulador e passa o novo valor do estado.
  </p>
  <p>
    Clicar nos botões <b>Show</b> e <b>Hide</b> fará com que a caixa apareça e desapareça instantaneamente sem quaisquer efeitos de animação.
    Os efeitos de animação de visibilidade padrão podem ser adicionados simplesmente substituindo a instrução <i>if</i> por uma chamada para <i>AnimatedVisibility</i> como segue:
  </p>
</div>

```kotlin
AnimatedVisibility(visible = boxVisible) {
  Box(
    modifier = Modifier
      .size(height = 200.dp, widht = 200.dp)
      .background(Color.Blue)
  )
}
```

<div align="center">

### O Preview:<br>
 
<img src="https://github.com/DeniseLeandroDeCastro/AnimateVisibility/assets/29150094/6108558e-1476-4653-b88a-ab45a46e2799"/>

</div>

<p>
Quando o aplicativo for testado, a ocultação e a exibição da caixa serão sutilmente animadas. O comportamento padrão do <b>AnimatedVisibility</b> é tão sutil que pode ser difícil notar qualquer diferença. Felizmente, a API Compose Animation oferece uma variedade de opções de personalização. A primeira opção permite que diferentes efeitos de animação sejam definidos quando os elementos secundários composables aparecem e desaparecem (chamados de animações de entrada e saída).
As animações a serem usadas quando os filhos de um elemento <i>Composable</i> AnimatedVisibility aparecem e desaparecem são declaradas usando os parâmetros enter e exit. As alterações a seguir, por exemplo, configuram as animações para que a caixa apareça gradualmente e deslize-a verticalmente para fora da visualização.
</p>

```kotlin

@Composable
fun MainScreen() {
    var boxVisible by remember { mutableStateOf(true) }
    val onClick = { newState: Boolean ->
        boxVisible = newState
    }
    Column(
        Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(
                text = "Show",
                targetState = false,
                onClick = onClick,
                bgColor = colorResource(id = R.color.steel_blue)

            )
            CustomButton(
                text = "Hide",
                targetState = true,
                onClick = onClick,
                bgColor = colorResource(id = R.color.light_steel_blue)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(
            visible = boxVisible,
            enter = fadeIn(),
            exit = slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(colorResource(id = R.color.steel_blue))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Box Visible",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

```

<p>
  O conjunto completo de efeitos de animação é o seguinte:
</p>
<ul>
  <li><b>expandHorizontally() -></b> O conteúdo é revelado usando uma técnica de recorte horizontal. Estão disponíveis opções para controlar quanto do conteúdo é revelado inicialmente antes do início da animação.</li>
  <li><b>expandVertically() -></b> O conteúdo é revelado usando uma técnica de recorte vertical. Estão disponíveis opções para controlar quanto do conteúdo é inicialmente revelado antes do início da animação.</li>
  <li><b>expandIn() -></b> O conteúdo é revelado usando técnicas de recorte horizontal e vertical. Estão disponíveis opções para controlar quanto do conteúdo é revelado inicialmente antes do início da animação.</li>
  <li><b>fadeIn() -></b> Desaparece o conteúdo de transparente para opaco. A transparência inicial (alfa) pode ser declarada usando um valor de ponto flutuante entre 0 e 1. o padrão é 0.</li>
  <li><b>fadeOut() -></b> Desaparece o conteúdo da vista, de opaco para invisível. A transparência inicial (alfa) pode ser declarada usando um valor de ponto flutuante entre 0 e 1. o padrão é 0.</li>
  <li><b>scaleIn() -></b> O conteúdo se expande como se um "zoom in" tivesse sido realizado. Por padrão, o conteúdo começa no tamanho zero e se expande para o tamanho total, pode ser alterado especificando o valor da escala inicial como um valor flutuante entre 0 e 1,0. </li>
  <li><b>scaleOut() -></b> Reduz o conteúdo do tamanho total para uma escala de destino especificada antes que ele desapareça. A escala alvo é 0 por padrão, mas pode ser configurada usando um valor flutuante entre 0 e 1,0.</li>
  <li><b>shrinkHorizontally() -></b> O conteúdo desliza da visualização atrás de uma linha vertical cada vez menor dos limites do clipe. A largura e direção do alvo podem ser configuradas.</li>
  <li><b>shrinkVertically() -></b> O conteúdo desliza da visualização atrás de uma linha de limites de clipe horizontal cada vez menor. A largura e direção do alvo podem ser configuradas.</li>
  <li><b>shrinkOut() -></b> O conteúdo desliza da visualização atrás de uma linha de limites de clipe horizontal e vertical cada vez menor.</li>
  <li><b>slideInHorizontally() -></b> O conteúdo é exibido ao longo do eixo horizontal. A direção do deslizamento e o deslocamento no conteúdo onde o deslizamento começa são personalizáveis.</li>
  <li><b>slideInVertically() -></b> O conteúdo é exibido ao longo do eixo vertical. A direção do deslizamento e o deslocamento no conteúdo onde o deslizamento começa são personalizáveis.</li>
  <li><b>slideIn() -></b> Desliza o conteúdo para visualização em um ângulo personalizável definido usando um valor de deslocamento inicial.</li>
  <li><b>slideOut() -></b> Desliza o conteúdo para fora da visualização em um ângulo personalizável definido usando um valor de deslocamento inicial.</li>
  <li><b>slideOutHorizontally() -></b> O conteúdo desliza para fora da visualização ao longo do eixo horizontal. A direção de deslizamento e o deslocamento dentro do conteúdo onde as extremidades são deslizantes são personalizáveis.</li>
  <li><b>slideOutVertically() -></b> O conteúdo desliza para fora da vista ao longo do eixo vertical. A direção de deslizamento e o deslocamento dentro do conteúdo onde as extremidades são deslizantes são personalizáveis.</li>
</ul>

### Exemplo de como fica o código utilizando as funções fadeIn() e fadeOut():

```kotlin

@Composable
fun MainScreen() {
    var boxVisible by remember { mutableStateOf(true) }
    val onClick = { newState: Boolean ->
        boxVisible = newState
    }
    Column(
        Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(
                text = "Show",
                targetState = false,
                onClick = onClick,
                bgColor = colorResource(id = R.color.steel_blue)
            )
            CustomButton(
                text = "Hide",
                targetState = true,
                onClick = onClick,
                bgColor = colorResource(id = R.color.light_steel_blue)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(
            visible = boxVisible,
            enter = fadeIn(),
            exit = fadeOut(animationSpec = tween(
                    durationMillis = 2000,
                    easing = LinearOutSlowInEasing))
        ) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(colorResource(id = R.color.steel_blue))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Box Visible",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

```

### Demonstração do app em execução, utilizando fadeIn() e fadeOut():

<div align="middle">
  
  [fadeIn_fadeOut.webm](https://github.com/DeniseLeandroDeCastro/AnimateVisibility/assets/29150094/eafe9b64-54ed-45fc-8bc1-5c0c82211926)
  
</div>






















