package us.createandlearn.huntthewumpus;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import us.createandlearn.R;

public class HuntTheWumpusActivity extends AppCompatActivity {
    private static final int TOSS_GRENADE_ANIMATION_DURATION = 700;

    View caveBackground;
    View caveBackground2;
    View wumpus;
    View bottomlessPit;
    TextView roomNumber;
    TextView grenadeCount;
    TextView wumpusCount;
    TextView resultText;
    Switch tossGrenadeSwitch;
    View newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hunt the Wumpus!");
        setContentView(R.layout.activity_hunt_the_wumpus);
        caveBackground = findViewById(R.id.cave_background);
        // This second background is used solely in the animation from one room to another.
        caveBackground2 = findViewById(R.id.cave_background2);
        wumpus = findViewById(R.id.wumpus);
        bottomlessPit = findViewById(R.id.bottomless_pit);
        tossGrenadeSwitch = findViewById(R.id.toss_grenade_switch);
        grenadeCount = findViewById(R.id.grenade_count);
        wumpusCount = findViewById(R.id.wumpus_count);
        roomNumber = findViewById(R.id.room_number);
        resultText = findViewById(R.id.result);
        newGameButton = findViewById(R.id.new_game);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
            }
        });
        initializeDirectionalButton(R.id.button_left, Direction.WEST);
        initializeDirectionalButton(R.id.button_right, Direction.EAST);
        initializeDirectionalButton(R.id.button_up, Direction.NORTH);
        initializeDirectionalButton(R.id.button_down, Direction.SOUTH);
        startNewGame();
    }

    private void initializeDirectionalButton(int buttonId, Direction direction) {
        // TODO: Implement this.
    }

    private void startNewGame() {
        // TODO: Implement this.
    }

    private void doGrenadeToss(Direction direction) {
        // TODO: Implement this.
    }

    private void doMove(Direction direction) {
        // TODO: Implement this.
    }

    private void showGameEndDialog(String endGameMessage) {
        new AlertDialog.Builder(HuntTheWumpusActivity.this)
                .setCancelable(false)
                .setMessage(endGameMessage)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startNewGame();
                    }
                }).show();
    }

    private void startGrenadeTossAnimation(Direction direction) {
        // Move the cave background back and forth horizontally so it looks like it's shaking.
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(caveBackground, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                .setDuration(TOSS_GRENADE_ANIMATION_DURATION);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                doGrenadeToss(direction);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
        animator.start();
    }

    private void startMoveAnimation(Direction direction) {
        // Have one image slide into the frame while another image slides out, so it looks like
        // the player is moving from one room to another.
        caveBackground.startAnimation(
                AnimationUtils.loadAnimation(HuntTheWumpusActivity.this, direction.inAnimationId));
        caveBackground2.startAnimation(
                AnimationUtils.loadAnimation(HuntTheWumpusActivity.this, direction.outAnimationId));
        caveBackground.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                doMove(direction);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}