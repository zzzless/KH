package study;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CalcFraction extends JFrame implements ActionListener{
	// 컨테이너
	Container c = getContentPane();
	// 빈레이블 + 선레이블
	//JLabel lblSpace = new JLabel();
	//JLabel lblLine = new JLabel("----------");
	// 좌항
	JTextField LNumerator = new JTextField("0"); // 분자 
	JTextField LDenominator = new JTextField("0"); // 분모
	JTextField LNumber = new JTextField("0"); // 자연수
	JPanel pLTerm = new JPanel(new GridLayout(0, 2));
	// 우항
	JTextField RNumerator = new JTextField("0"); // 분자 
	JTextField RDenominator = new JTextField("0"); // 분모
	JTextField RNumber = new JTextField("0"); // 자연수
	JPanel pRTerm = new JPanel(new GridLayout(0, 2));
	// 부호
	JButton btnSign = new JButton("+");
	JPanel pSign = new JPanel();
	// 식이 들어갈 패널
	JPanel pNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
	// -----------------------------------------------------------
	// 등호
	JButton btnEquals = new JButton("=");
	JPanel pEquals = new JPanel();
	// 결과분수
	JTextField Numerator = new JTextField("0"); // 분자 
	JTextField Denominator = new JTextField("0"); // 분모
	JTextField Number = new JTextField("0"); // 자연수
	JPanel pResult = new JPanel(new GridLayout(0, 2));
	// 결과가 들어갈 패널
	JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	public CalcFraction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("분수계산기");
		setSize(600,200);
		
		setUI();
		
		setVisible(true);
	}
	
	private void setUI() {
		// 좌항분수 세팅
		pLTerm.add(new JLabel());
		pLTerm.add(LNumerator);
		pLTerm.add(LNumber);
		pLTerm.add(new JLabel("-----------------------------"));
		pLTerm.add(new JLabel());
		pLTerm.add(LDenominator);
		// 부호 버튼
		btnSign.addActionListener(this);
		pSign.add(btnSign);
		// 우항분수 세팅
		pRTerm.add(new JLabel());
		pRTerm.add(RNumerator);
		pRTerm.add(RNumber);
		pRTerm.add(new JLabel("-----------------------------"));
		pRTerm.add(new JLabel());
		pRTerm.add(RDenominator);
		// 계산식 셋팅
		pNorth.add(pLTerm);
		pNorth.add(pSign);
		pNorth.add(pRTerm);
		c.add(pNorth,BorderLayout.CENTER);
		// 결과식 세팅
		btnEquals.addActionListener(this);
		pEquals.add(btnEquals);
		pResult.add(new JLabel());
		pResult.add(Numerator);
		pResult.add(Number);
		pResult.add(new JLabel("-----------------------------"));
		pResult.add(new JLabel());
		pResult.add(Denominator);
		pSouth.add(pEquals);
		pSouth.add(pResult);
		c.add(pSouth, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		new CalcFraction();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSign)) {
			switch (btnSign.getText()) {
			case "+":
				btnSign.setText("-");
				break;
			case "-":
				btnSign.setText("*");
				break;
			case "*":
				btnSign.setText("/");
				break;
			case "/":
				btnSign.setText("+");
				break;
			}
		} else if(e.getSource().equals(btnEquals)) {
			calculation(btnSign.getText());
		}
	}
	
	public void calculation(String b) {
		int LNumb = 0;  // 자연수 
		int LNume = 0;  // 분자
		int LDeno = 0;  // 분모
		int RNumb = 0;  // 자연수 
		int RNume = 0;  // 분자
		int RDeno = 0;  // 분모
		
		try {
			LNumb = Integer.parseInt(LNumber.getText());
			LNume = Integer.parseInt(LNumerator.getText());
			LDeno = Integer.parseInt(LDenominator.getText());
			RNumb = Integer.parseInt(RNumber.getText());
			RNume = Integer.parseInt(RNumerator.getText());
			RDeno = Integer.parseInt(RDenominator.getText());
			
		} catch(NumberFormatException e) {
			System.out.println("숫자 변환 오류");
		}
		
		if(LNumb != 0) { // 좌항 자연수가 있을때
			LNume = LNume + (LNumb * LDeno); // 분모와 자연수를 곱하여 분자에 더한다.
		}
		if(RNumb != 0) { // 우항 자연수가 있을때
			RNume = RNume + (RNumb * RDeno); // 분모와 자연수를 곱하여 분자에 더한다.
		}
		
		int nume = 0;
		int deno = 0;
		int numb = 0;
		switch(b) {
		case "+":
			nume = (LNume*RDeno)+(RNume*LDeno);
			deno = RDeno*LDeno;
			numb = nume / deno;
			nume %= deno; 
			break;
		case "-":
			if(RDeno != LDeno) { // 분모를 같지 않다면 분모를 같게해준다.
				int tmp = RDeno;
				RDeno *= LDeno;
				RNume *= LDeno;
				LDeno *= tmp;
				LNume *= tmp;
			}
			break;
		case "*":
			
			break;
		case "/":
			
			break;
		}
		Numerator.setText(String.valueOf(nume));
		Denominator.setText(String.valueOf(deno));
		Number.setText(String.valueOf(numb));
	}
}
